package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dto.NewAssignmentDto;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import by.htp.basumatarau.jd2TestSystem.service.TestService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/assignment-manager/new-assignment")
public class NewAssignmentController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET)
    public String showNewAssignment(
            Model model,
            @RequestParam(value = "page", required = false) Integer page)
            throws ServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();

        Set<User> followers = userService.getFollowers(currentUser);
        List<Test> testForUsers = testService.getTestForUsers(currentUser);

        long totalNumberOfTests = testService.getTotalNumberOfTests();
        int topPageEntry = 0;
        if(page!=null){
            topPageEntry = (page - 1)*ENTRIES_PER_PAGE;
        }
        List<Test> tests = testService.getTests(topPageEntry, ENTRIES_PER_PAGE);

        model.addAttribute("startpage",1);
        model.addAttribute("endpage", ((int) (1 + (totalNumberOfTests / ENTRIES_PER_PAGE))));
        model.addAttribute("tests", tests);

        model.addAttribute("newAssignmentDetails", new NewAssignmentDto());
        model.addAttribute("testsForUser", testForUsers);
        model.addAttribute("followers", followers);

        //todo test assignment view
        return "new-assignment";
    }

    @RequestMapping(value = "/processNewAssignment", method = RequestMethod.POST)
    public String processNewAssignment(
            @ModelAttribute("newAssignmentDetails") NewAssignmentDto dto) throws ServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();



        for (Integer id : dto.getAssigneeIds()) {
            Assignment newAssignment = new Assignment();
            newAssignment.setName(dto.getName());
            newAssignment.setDetails(dto.getDetails());
            newAssignment.setMasterTest(testService.getTestById(dto.getAssignedTestId()));
            newAssignment.setAssigner(currentUser);

            newAssignment.setDeadline(new Date());
            newAssignment.setSubmitted(false);
            newAssignment.setAssigner(currentUser);
            //todo get follower by id instead of getUser - to be fixed
            newAssignment.setAssignee(userService.getUserByUserId(id));
            assignmentService.createNewAssignment(newAssignment);
        }

        //todo test assignment view
        return "redirect:/assignment-manager";
    }

}
