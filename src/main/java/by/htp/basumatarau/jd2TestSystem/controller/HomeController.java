package by.htp.basumatarau.jd2TestSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String homePage(Model model, Principal principal){
        return "home";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/subscribers")
    public String subscribers(){
        return "subscribers";
    }

    @RequestMapping("/subscription")
    public String subscription(){
        return "subscription";
    }

    @RequestMapping("/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
