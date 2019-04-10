package by.htp.basumatarau.jd2TestSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class HomeController {

    @ExceptionHandler
    public String pageNotFound(HttpServletRequest req, Exception ex){
        return "page-not-found-404";
    }

    @RequestMapping(value = "/")
    public String homePage(Model model, Principal principal){
        return "home";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
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
