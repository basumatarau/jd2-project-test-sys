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
public class SubscriptionController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/subscription", method = RequestMethod.GET)
    public String subscription(
            @RequestParam(value = "page", required = false) Integer page,
            Model model,
            Principal principal) throws ServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();

        int topPageEntry = 0;
        if(page!=null){
            topPageEntry = (page - 1) * ENTRIES_PER_PAGE;
        }

        Set<User> followedUsers = userService.getFollowedUsers(currentUser);
        List<User> userList = userService.getUsers(topPageEntry, ENTRIES_PER_PAGE);
        userList.remove(currentUser);

        long totalUsersCount = userService.getTotalUsersCount();

        model.addAttribute("startpage",1);
        model.addAttribute("endpage", ((int) (1 + (totalUsersCount / ENTRIES_PER_PAGE))));
        model.addAttribute("userList", userList);

        model.addAttribute("followedUsers", followedUsers);

        return "subscription";
    }

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public String cnangeSubscription(
            @RequestParam(value = "id") Integer uid,
            @RequestParam(value = "action") String subscribeAction,
            Model model,
            Principal principal) throws ServiceException {

        CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userService.getUserByUserId(customUser.getId());

        if(subscribeAction.equals("subscribe")) {
            userService.addFollowedUser(currentUser, userService.getUserByUserId(uid));

        }
        if(subscribeAction.equals("unsubscribe")) {
            userService.removeFollowedUser(currentUser, userService.getUserByUserId(uid));
        }

        return subscription(1, model, principal);
    }
}
