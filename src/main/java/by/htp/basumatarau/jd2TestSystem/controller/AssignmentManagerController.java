package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dao.TestDao;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/assignment-manager")
public class AssignmentManagerController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestService testService;

    @RequestMapping
    public String assignmentManager(
            @RequestParam(value = "page", required = false) Integer page,
                    Model model,
                    Principal principal) throws ServiceException{
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
            Principal principal) throws ServiceException{

        assignmentService.deleteAssignment(assignmentService.getAssignmentById(id));
        return assignmentManager(1, model, principal);
    }

    @RequestMapping(value = "/new-assignment", method = RequestMethod.GET)
    public String subscribers(
            @RequestParam(value = "page", required = false) Integer page,
            Model model,
            Principal principal) throws ServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.getUserByUserId(customUser.getId());

        Set<User> followers = userService.getFollowers(currentUser);
        //fetch test for the current user(+public tests)
        //implement functionality: assign test to one or more users (assignees)
        long totalNumberOfTests = testService.getTotalNumberOfTests();

        int topPageEntry = 0;
        if(page!=null){
            topPageEntry = (page - 1)*ENTRIES_PER_PAGE;
        }
        List<Test> testForUsers = testService.getTestForUsers(currentUser);
        List<Test> tests = testService.getTests(topPageEntry, ENTRIES_PER_PAGE);

        model.addAttribute("startpage",1);
        model.addAttribute("endpage", ((int) (1 + (totalNumberOfTests/ ENTRIES_PER_PAGE))));

        model.addAttribute("tests", tests);
        model.addAttribute("testsForUser", testForUsers);
        model.addAttribute("followers", followers);

        //todo test assignment view
        return "new-assignment";
    }

}
