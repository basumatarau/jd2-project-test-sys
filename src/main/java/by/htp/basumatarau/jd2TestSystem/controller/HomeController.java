package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.dao.exception.UserCredentialsNotRegistered;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

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
            @RequestParam(value = "firstNameInput") String firstName,
            @RequestParam(value = "lastNameInput") String lastName,
            @RequestParam(value = "emailinput") String email,
            @RequestParam(value = "passwordinput") String passowrd
    ) throws ServiceException{

        ModelAndView mav = new ModelAndView();
        try {
            if (userService.getUserByUserEmail(email) != null) {

                mav.addObject("occupiedCredentials", "true");
                mav.setViewName("sign-up");
                return mav;
            }
        }catch (UserServiceException e){
            //do nothing
        }

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEnabled(true);
        user.setPasswordHash(passwordEncoder.encode(passowrd));

        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUser(user);

        user.setAuthoritySet(Set.of(authority));
        userService.createNewUser(user);

        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUpPage(){
        return "sign-up";
    }

    @RequestMapping(value = "/subscribers")
    public String subscribers(){
        return "subscribers";
    }

    @RequestMapping(value = "/subscription")
    public String subscription(){
        return "subscription";
    }

    @RequestMapping(value = "/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
