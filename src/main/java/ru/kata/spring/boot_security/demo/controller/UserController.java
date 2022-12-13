package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServiceImp userService;
    private final UserValidator userValidator;

    public UserController(UserServiceImp userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("")
    public String loginPage(){
        return "user";
    }

    @GetMapping("/info")
    public String showInfo(Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        System.out.println(user);
        model.addAttribute("user", user);
        return "userInfoPage";
    }
}