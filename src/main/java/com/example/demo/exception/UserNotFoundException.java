package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessOpsException {
    public UserNotFoundException(int id, HttpStatus httpStatus) {
        super("User not found: " + id, httpStatus);
    }
}
