package com.lairon.laberis.service.password.impl;

import com.lairon.laberis.service.password.PasswordService;
import com.lairon.laberis.service.password.exception.SimplePasswordException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPasswordServiceTest {

    private PasswordService service = new DefaultPasswordService();

    @Test
    void checkValidPassword() {
        assert service.checkPassword("Admin123");
    }

    @Test
    void checkNonNumbersPassword() {
        assertThrows(SimplePasswordException.class, () -> service.checkPassword("Admin")) ;
    }

    @Test
    void checkNonUpperLettersPassword() {
        assertThrows(SimplePasswordException.class, () -> service.checkPassword("admin123")) ;
    }

    @Test
    void checkNonUpperLettersNonNumbersPassword() {
        assertThrows(SimplePasswordException.class, () -> service.checkPassword("admin")) ;
    }

    @Test
    void checkNonLengthPassword() {
        assertThrows(SimplePasswordException.class, () -> service.checkPassword("1aA")) ;
    }

    @Test
    void hashPassword() {
        assert service.hashPassword("testPassword1233").equals("f2d6dd72a56ecac2c8b4fad5155eb2d2dcb617b8d8623255df7c110fbcc4b934");
    }
}