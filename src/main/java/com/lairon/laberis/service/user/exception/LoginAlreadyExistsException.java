package com.lairon.laberis.service.user.exception;

import org.springframework.http.HttpStatusCode;

public class LoginAlreadyExistsException extends UserException {

    public LoginAlreadyExistsException() {
        super(HttpStatusCode.valueOf(603), "Login is already exists");
    }
}
