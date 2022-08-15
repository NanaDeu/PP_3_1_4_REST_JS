package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String pageForAdmin(Principal principal, ModelMap model) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);

        model.addAttribute("userList", userService.getAllUsers());

        //new
        model.addAttribute("newUser", new User());
        model.addAttribute("listRoles", userService.listRoles());

        //edit
/*        model.addAttribute("editUser", userService.getUserById(id));
        model.addAttribute("listRoles", userService.listRoles());*/

        return "admin";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("newUser") User newUser) {
        userService.save(newUser);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

/*    @GetMapping("/edit")
    @ResponseBody
    public String updateUser(long id, Model model) {
        model.addAttribute("editUser", userService.getUserById(id));
        model.addAttribute("listRoles", userService.listRoles());
        return "redirect:/admin";
    }

    @PatchMapping("/edit")
    public String updateUser(@ModelAttribute("editUser") User user, long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }*/

/*    @Controller
    @RequestMapping("/admin/users")
    public class AdminController*/

   /* @GetMapping()
    public String printUsers(ModelMap model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("userList", list);
        return "/users";
    }


    @GetMapping("/new")
    public String addUserForm(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", userService.listRoles());
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", userService.listRoles());
        return "/edit";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }*/

}
