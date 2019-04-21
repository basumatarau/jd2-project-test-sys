package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.TestService;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/my-resources")
public class MyResourcesController {

    @Autowired
    private TestService testService;

    @RequestMapping
    public String resources(
            Model model,
            Principal principal) throws ServiceException {
        //
        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();

        List<Test> testsForUser = testService.getTestForUsers(currentUser);
        model.addAttribute("myTests", testsForUser);

        return "my-resources";
    }

    @RequestMapping(value = "/newTest", method = RequestMethod.GET)
    public String newTest() throws ServiceException {
        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();

        //static view
        return "new-test";
    }
}
