package ru.kata.spring.boot_security.demo.util;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Component
public class UserValidator implements Validator {
    private final UserServiceImpl personDetailsService;

    public UserValidator(UserServiceImpl personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals((clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            personDetailsService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("username", "Error", "Имя пользователя уже занято");
    }
}