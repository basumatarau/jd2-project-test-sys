package by.htp.basumatarau.jd2TestSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String indexPage(Model model, Principal principal){
        model.addAttribute("message",
                "You are logged in as " + principal.getName());
        return "index";
    }

}
