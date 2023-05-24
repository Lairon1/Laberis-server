package com.lairon.laberis.service.password.impl;

import com.lairon.laberis.service.password.PasswordService;
import com.lairon.laberis.service.password.exception.PasswordException;
import com.lairon.laberis.service.password.exception.SimplePasswordException;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DefaultPasswordService implements PasswordService {

    /**
     * Пароль должен содержать хотя бы одну цифру [0-9].
     * Пароль должен содержать хотя бы один строчный латинский символ [az].
     * Пароль должен содержать хотя бы один заглавный латинский символ [AZ].
     * Пароль должен содержать не менее 8 символов и не более 20 символов.
     */
    private final Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");

    @Override
    public boolean checkPassword(@NonNull String password) throws PasswordException {
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new SimplePasswordException();
        return true;
    }

    @Override
    public String hashPassword(@NonNull String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = md.digest(password.getBytes());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }

        return sb.toString();
    }
}
