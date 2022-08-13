package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Here you can take a look at all Users(if you are an admin)");
        model.addAttribute("messages", messages);
        return "login";
    }
/*    @GetMapping("/admin")
    public String pageForAdmin(Principal principal, ModelMap model) {
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);

        List<User> list = userService.getAllUsers();
        model.addAttribute("userList", list);

        //new
        model.addAttribute("newUser", new User());
        model.addAttribute("listRoles", userService.listRoles());
        return "admin";
    }
    @PostMapping("/admin")
    public String addUser(@ModelAttribute("newUser") User newUser) {
        userService.save(newUser);
        return "admin";
    }*/
    @GetMapping("/user")
    public String pageForUser(Principal principal, ModelMap model) {
        /*User user = userService.findUserByUsername(principal.getName());*/
        model.addAttribute("userDescription", userService.findUserByUsername(principal.getName()).toString());
        return "user";
    }
}
