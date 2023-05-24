package com.lairon.laberis.service.token.impl;

import com.lairon.laberis.repository.UserRepository;
import com.lairon.laberis.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class DefaultTokenService implements TokenService {

    private final int TOKEN_LENGTH = 64;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        random.nextBytes(tokenBytes);
        BigInteger tokenInt = new BigInteger(1, tokenBytes);
        String token = tokenInt.toString(16);
        while (token.length() < TOKEN_LENGTH * 2) {
            token = "0" + token;
        }

        if(userRepository.findByToken(token).isPresent())
            return generateToken();

        return token;
    }
}
