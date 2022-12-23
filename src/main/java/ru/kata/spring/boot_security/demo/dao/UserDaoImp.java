package ru.kata.spring.boot_security.demo.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Component
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder;

    public UserDaoImp(EntityManager entityManager, @Lazy PasswordEncoder passwordEncoder) {
        this.em = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Deprecated
    public List<User> getAllUsers() {
        return em.createQuery("from User").getResultList();
    }

    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
//        Role role_user = new Role(2, "ROLE_USER");
//        Role role_user = new Role(1, "ROLE_ADMIN");
//        user.setRole(Collections.singletonList(role_user));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        em.persist(user);
        em.persist(user);
    }

    @Override
    public void update(long id, User user) {
        User userForUpdate = getUser(id);
        userForUpdate.setUsername(user.getUsername());
        userForUpdate.setSurname(user.getSurname());
        userForUpdate.setAge(user.getAge());
        userForUpdate.setPhoneNumber(user.getPhoneNumber());
        userForUpdate.setRole(user.getRole());
    }

    @Override
    public void delete(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}