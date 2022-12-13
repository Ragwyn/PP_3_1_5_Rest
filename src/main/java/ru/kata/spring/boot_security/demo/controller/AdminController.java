package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private final UserValidator userValidator;
    @Autowired
    public AdminController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public String printUsers(ModelMap model, Principal principal, @ModelAttribute("user") User user) {
        User user1 = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user1);
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("rolesList", userService.listRoles());
        model.addAttribute("newUser", new User());
        return "adminPage";
    }
    @GetMapping("/{id}")
    public String showInfo(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "adminInfoPage";
    }
    @PostMapping("/add_user")
    public String create(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("roleList", userService.listRoles());
            return "redirect:/admin/allUsers";
        }
        userService.save(user);
        return "redirect:/admin/allUsers";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("user") @Valid User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin/allUsers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/allUsers";
    }

}