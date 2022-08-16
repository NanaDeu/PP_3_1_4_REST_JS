package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

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

        return "admin";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("userTable") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);

        return "redirect:/admin";
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
}
