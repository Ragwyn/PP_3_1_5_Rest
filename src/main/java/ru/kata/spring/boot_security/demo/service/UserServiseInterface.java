package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserServiseInterface {

    @Transactional
    User findUserByUserName(String username);

    List<User> findAll();

    Optional<User> findById(long id);

    void save(User user);

    void edit(User user);

    void remove(long id);
    User findUserByEmail(String username);
    User findByEmail(String email);

}