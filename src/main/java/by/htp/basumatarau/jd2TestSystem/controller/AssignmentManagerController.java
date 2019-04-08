package by.htp.basumatarau.jd2TestSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AssignmentManagerController {

    @RequestMapping("/assignment-manager")
    public String assigmentManager(){
        return "assignment-manager";
    }

    @RequestMapping("/assignment-manager/test-bank")
    public String testBankPage(){
        return "test-bank";
    }

    @RequestMapping("/assignment-manager/my-tests")
    public String myTestsPage(){
        return "my-tests";
    }

    @RequestMapping("/assignment-manager/test-constructor")
    public String newAssignment(){
        return "test-constructor";
    }

}
