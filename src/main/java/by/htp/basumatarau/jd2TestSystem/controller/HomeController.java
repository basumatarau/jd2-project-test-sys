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

}
