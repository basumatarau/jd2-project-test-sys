package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class SubscribersController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/subscribers", method = RequestMethod.GET)
    public String subscribers(
            @RequestParam(value = "page", required = false) Integer page,
            Model model,
            Principal principal) throws ServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.getUserByUserId(customUser.getId());

        Set<User> followers = userService.getFollowers(currentUser);

        model.addAttribute("followers", followers);

        return "subscribers";
    }

}
