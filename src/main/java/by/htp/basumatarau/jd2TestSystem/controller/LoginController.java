package by.htp.basumatarau.jd2TestSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/access-denied")
    public String accessDeniedPage(){
        return "access-denied";
    }
}
