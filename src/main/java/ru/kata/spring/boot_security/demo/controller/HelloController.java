package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "index";
    }

    @GetMapping("/user")
    public String pageForUser(Principal principal, ModelMap model) {
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("userDescription", user.toString());

        return "user";
    }

}
