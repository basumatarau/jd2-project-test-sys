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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
    public String showNewAssignmentConstructor(
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

        return "new-assignment";
    }

    @RequestMapping(value = "/processNewAssignment", method = RequestMethod.POST)
    public String processNewAssignment(
            @ModelAttribute("newAssignmentDetails") @Valid NewAssignmentDto dto,
            BindingResult bindingResult,
            Model model) throws ServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();

        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors()
                    .forEach(error -> model.addAttribute(error.getField(), error.getDefaultMessage()));

            model.addAttribute("newAssignmentErrors", bindingResult.getFieldErrors());

            return showNewAssignmentConstructor(model, 1);
        }

        Test assignedTest = testService.getTestById(dto.getAssignedTestId());
        for (Integer id : dto.getAssigneeIds()) {
            Assignment newAssignment = new Assignment();
            newAssignment.setName(dto.getName());
            newAssignment.setDetails(dto.getDetails());
            newAssignment.setMasterTest(assignedTest);
            newAssignment.setAssigner(currentUser);
            newAssignment.setDeadline(dto.getDueDate());
            newAssignment.setSubmitted(false);
            newAssignment.setAssigner(currentUser);
            newAssignment.setAssignee(userService.getUserByUserId(id));
            assignmentService.createNewAssignment(newAssignment);
        }

        return "redirect:/assignment-manager";
    }

}
