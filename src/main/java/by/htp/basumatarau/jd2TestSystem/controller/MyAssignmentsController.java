package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.model.*;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping(value = "/my-assignments")
public class MyAssignmentsController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @RequestMapping
    public String myAssignments(
            @RequestParam(value = "page", required = false) Integer page,
            Model model,
            Principal principal) throws UserServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User assignee = userService.getUserByUserId(customUser.getId());

        long assignmentListSize = assignmentService.getNumberOfAssignmentsForAssignee(assignee);

        int topPageEntry = 0;
        if(page!=null){
            topPageEntry = (page - 1)*ENTRIES_PER_PAGE;
        }

        List<Assignment> assignmentList = null;
        if(assignmentListSize != 0){
            assignmentList = assignmentService
                                .getAssignmentsForAssignee(
                                        assignee,
                                        topPageEntry,
                                        ENTRIES_PER_PAGE
                                );
        }

        model.addAttribute("startpage",1);
        model.addAttribute("endpage", ((int) (1 + (assignmentListSize / ENTRIES_PER_PAGE))));
        model.addAttribute("assignmentList", assignmentList);

        return "my-assignments";
    }


    @RequestMapping(value = "/start")
    public String startAssignment(
            @RequestParam(value = "id") Integer id,
            Model model,
            Principal principal){
        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();

        model.addAttribute("id", id);

        return "test-submission-page";
    }
}
