package com.example.minisaraha.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameAlreadyTakenException extends AuthenticationException {
    public UsernameAlreadyTakenException(String msg) {
        super(msg);
    }
}