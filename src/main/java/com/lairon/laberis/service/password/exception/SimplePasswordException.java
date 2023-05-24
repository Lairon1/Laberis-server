package com.lairon.laberis.service.password.exception;

import org.springframework.http.HttpStatusCode;

public class SimplePasswordException extends PasswordException {
    public SimplePasswordException() {
    super(HttpStatusCode.valueOf(601), "Password is very simple");
    }
}
