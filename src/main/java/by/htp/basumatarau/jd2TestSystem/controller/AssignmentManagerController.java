package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AssignmentManagerController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @RequestMapping("/assignment-manager")
    public String assigmentManager(Model model, Principal principal){
        String name = principal.getName();
        model.addAttribute("username", name);
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("id", customUser.getId());
        model.addAttribute("email", customUser.getEmail());


        List<Assignment> assignmentList
                = assignmentService.getAssignmentsForAssigner(
                        userService.getUserByUserId(customUser.getId())
        );

        model.addAttribute("assignmentList", assignmentList);
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
