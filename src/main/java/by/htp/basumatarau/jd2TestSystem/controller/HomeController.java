package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dto.UserDto;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserCredentialsOccupied;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @ModelAttribute("accountDetails")
    public UserDto signUpFromBackingObject(){
        return new UserDto();
    }

    @RequestMapping(value = "/")
    public String homePage(Model model, Principal principal){
        return "home";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ModelAndView registration(
            @ModelAttribute("accountDetails") @Valid UserDto dto,
            BindingResult bindingResult
    ) {
        ModelAndView mav = new ModelAndView();

        if(bindingResult.hasErrors()){
            mav.addObject("bindingErrors", bindingResult.getFieldErrors());
            mav.setViewName("sign-up");
            return mav;
        }

        try {
            userService.registerNewUser(dto);
        }catch (UserCredentialsOccupied e){
            mav.addObject("occupiedCredentials", "true");
            mav.setViewName("sign-up");
            //todo log the sign-up attempt with occupied credentials
            return mav;
        }catch (ServiceException e){
            mav.addObject("signUpError", "registration attempt has been failed");
            mav.setViewName("sign-up");
            return mav;
        }
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "update", required = false) Integer idUpdate,
            @RequestParam(value = "delete", required = false) Integer idDelete,
            Model model,
            Principal principal) throws UserServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();

        if(idUpdate!=null){
            User userByUserId = userService.getUserByUserId(idUpdate);
            userService.update(userByUserId);
        }

        if(idDelete!=null){
            User userByUserId = userService.getUserByUserId(idDelete);
            userService.delete(userByUserId);
        }

        int topPageEntry = 0;
        if(page!=null){
            topPageEntry = (page - 1) * ENTRIES_PER_PAGE;
        }
        List<User> userList = userService.getUsers(topPageEntry, ENTRIES_PER_PAGE);
        long totalUsersCount = userService.getTotalUsersCount();

        model.addAttribute("startpage",1);
        model.addAttribute("endpage", ((int) (1 + (totalUsersCount / ENTRIES_PER_PAGE))));
        model.addAttribute("userList", userList);

        return "admin-page";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUpPage(){
        return "sign-up";
    }

    @RequestMapping(value = "/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
