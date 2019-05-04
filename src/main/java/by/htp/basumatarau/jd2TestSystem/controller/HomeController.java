package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dto.UserDto;
import by.htp.basumatarau.jd2TestSystem.dto.AdminFormDto;
import by.htp.basumatarau.jd2TestSystem.service.RoleService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserCredentialsOccupied;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RoleService roleService;

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

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUpPage(){
        return "sign-up";
    }

    @RequestMapping(value = "/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
