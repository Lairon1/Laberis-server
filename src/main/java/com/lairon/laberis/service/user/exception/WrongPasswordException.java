package com.lairon.laberis.service.user.exception;

import org.springframework.http.HttpStatusCode;

public class WrongPasswordException extends UserException {
    public WrongPasswordException() {
        super(HttpStatusCode.valueOf(602), "Wrong password");
    }
}
