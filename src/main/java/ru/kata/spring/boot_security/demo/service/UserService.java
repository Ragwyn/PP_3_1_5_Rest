package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser (long id);
    void save(User user);
    public void update(long id, User user);
    void delete(long id);

    public List<Role> listRoles();
    public User getUserByUsername(String username);
}