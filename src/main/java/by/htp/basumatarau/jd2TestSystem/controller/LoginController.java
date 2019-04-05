package by.htp.basumatarau.jd2TestSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping
    public String loginPage(){

        return "login-page";
    }

    @GetMapping
    public String accessDeniedPage(){

        return "access-denied";
    }
}
