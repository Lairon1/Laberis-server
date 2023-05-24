package com.lairon.laberis.service.password.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class PasswordException extends ResponseStatusException {

    public PasswordException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
