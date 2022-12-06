package ru.kata.spring.boot_security.demo.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotExistsException extends AuthenticationException {
    public UserNotExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNotExistsException(String msg) {
        super(msg);
    }
}