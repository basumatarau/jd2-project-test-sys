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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String assignmentManager(
            @RequestParam(value = "page", required = false) Integer page,
                    Model model) {
        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User assigner = customUser.getCurrentUser();

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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAssignment(
            @RequestParam("id") Integer id,
            Model model){
        assignmentService.deleteAssignment(assignmentService.getAssignmentById(id));
        return assignmentManager(1, model);
    }

    @RequestMapping(value = "/showResult", method = RequestMethod.GET)
    public String showResult(
            @RequestParam("id") Integer id,
            Model model){
        Assignment assignmentDetailed = assignmentService.getAssignmentAndSubmittedQuestions(id);
        model.addAttribute("submittedAssignment", assignmentDetailed);
        return "assignment-results";
    }
}
