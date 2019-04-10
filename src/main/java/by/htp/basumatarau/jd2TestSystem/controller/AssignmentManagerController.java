package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/assignment-manager")
public class AssignmentManagerController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @RequestMapping
    public String assigmentManager(
            @RequestParam(value = "page", required = false) Integer page,
                    Model model,
                    Principal principal){
        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User assigner = userService.getUserByUserId(customUser.getId());
        long assignmentListSize = assignmentService.getNumberOfManagedAssignments(assigner);

        int topPageEntry = 0;
        if(page!=null){
            topPageEntry = (page - 1)*ENTRIES_PER_PAGE;
        }
        List<Assignment> assignmentList
                = assignmentService.getAssignmentsForAssigner(assigner, topPageEntry, ENTRIES_PER_PAGE);

        model.addAttribute("startpage",1);
        model.addAttribute("endpage", ((int) (1 + (assignmentListSize / ENTRIES_PER_PAGE))));
        model.addAttribute("assignmentList", assignmentList);

        return "assignment-manager";
    }

    @RequestMapping(value = "/deleteAssignment")
    public String deleteAssignment(
            @RequestParam("id") Integer id,
            Model model,
            Principal principal){

        assignmentService.deleteAssignment(assignmentService.getAssignmentById(id));
        return assigmentManager(1, model, principal);
    }

    @RequestMapping(value = "/test-bank")
    public String testBankPage(){
        return "test-bank";
    }

    @RequestMapping(value = "/my-tests")
    public String myTestsPage(){
        return "my-tests";
    }

    @RequestMapping(value = "/test-constructor")
    public String newAssignment(){
        return "test-constructor";
    }

}