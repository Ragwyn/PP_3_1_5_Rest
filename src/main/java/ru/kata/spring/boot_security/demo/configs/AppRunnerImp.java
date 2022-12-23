package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Component
public class AppRunnerImp implements ApplicationRunner{
    @PersistenceContext
    private EntityManager entityManager;
    private final UserService userService;

    public AppRunnerImp(UserService userService) {
        this.userService = userService;
    }
    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            Role role1 = new Role(1,"ROLE_ADMIN"); //("ROLE_ADMIN")
            Role role2 = new Role(2,"ROLE_USER");
            User user1 = new User("admin", "admin", 31, "admin", "admin");
            User user2 = new User("user", "user", 21, "user", "user");
            user1.setRole(Collections.singletonList(role1));
            user2.setRole(Collections.singletonList(role2));
            entityManager.persist(role1);
            entityManager.persist(role2);
            entityManager.persist(user1);
            entityManager.persist(user2);
        }
    }
}