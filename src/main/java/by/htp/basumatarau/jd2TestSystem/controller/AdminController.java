package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dto.AdminFormDto;
import by.htp.basumatarau.jd2TestSystem.model.Role;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.RoleService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("adminFormDetails")
    public AdminFormDto adminFromBackingObject(){
        return new AdminFormDto();
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(
            @RequestParam(value = "page", required = false) Integer page,
            Model model) throws UserServiceException {

        int topPageEntry = 0;
        if(page!=null){
            topPageEntry = (page - 1) * ENTRIES_PER_PAGE;
        }
        List<User> userList = userService.getUsers(topPageEntry, ENTRIES_PER_PAGE);
        long totalUsersCount = userService.getTotalUsersCount();

        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("startpage",1);
        model.addAttribute("endpage", ((int) (1 + (totalUsersCount / ENTRIES_PER_PAGE))));
        model.addAttribute("userList", userList);

        return "admin-page";
    }

    @RequestMapping(value = "/admin/processAdminFormUpdate", method = RequestMethod.POST)
    public String adminActionUpdate(
            @ModelAttribute(value = "adminFormDetails") @Valid AdminFormDto dto,
            BindingResult bindingResult,
            Model model) throws UserServiceException {

        if(bindingResult.hasErrors()){
            model.addAttribute("bindingErrors", bindingResult.getFieldErrors());
            return adminPage(1, model);
        }
        User user = userService.getUserByUserId(dto.getId());

        Set<Role> roles = dto.getRoles()
                .stream()
                .filter(r -> Objects.nonNull(r.getRoleName()))
                .map(roleDto -> roleService.getRoleByName(roleDto.getRoleName()))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        userService.update(user);

        return adminPage(1, model);
    }

    @RequestMapping(value = "/admin/processAdminFormDelete", method = RequestMethod.POST)
    public String adminActionDelete(
            @ModelAttribute(value = "adminFormDetails") @Valid AdminFormDto dto,
            BindingResult bindingResult,
            Model model) throws UserServiceException {

        if(bindingResult.hasErrors()){
            model.addAttribute("bindingErrors", bindingResult.getFieldErrors());
            return adminPage(1, model);
        }
        User user = userService.getUserByUserId(dto.getId());
        userService.delete(user);

        return adminPage(1, model);
    }
}
