package com.lairon.laberis.service.user.impl;

import com.lairon.laberis.domain.User;
import com.lairon.laberis.domain.UserRole;
import com.lairon.laberis.repository.UserRepository;
import com.lairon.laberis.service.password.PasswordService;
import com.lairon.laberis.service.password.exception.PasswordException;
import com.lairon.laberis.service.token.TokenService;
import com.lairon.laberis.service.user.UserService;
import com.lairon.laberis.service.user.exception.LoginAlreadyExistsException;
import com.lairon.laberis.service.user.exception.UserException;
import com.lairon.laberis.service.user.exception.WrongPasswordException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordService passwordService;

    @Override
    public User register(@NonNull String login,
                         @NonNull String password,
                         @NonNull String firstname,
                         @NonNull String lastname) throws UserException, PasswordException {

        if (userRepository.findById(login).isPresent())
            throw new LoginAlreadyExistsException();

        passwordService.checkPassword(password);
        String hashPassword = passwordService.hashPassword(password);

        User user = User
                .builder()
                .login(login)
                .password(hashPassword)
                .firstname(firstname)
                .lastname(lastname)
                .role(UserRole.USER)
                .token(tokenService.generateToken())
                .build();

        userRepository.save(user);

        return user;
    }

    @Override
    public Optional<User> login(@NonNull String login,
                                @NonNull String password) throws UserException {
        Optional<User> findUser = userRepository.findByLogin(login);
        if (!findUser.isPresent())
            return Optional.empty();

        User user = findUser.get();
        if(!user.getPassword().equals(passwordService.hashPassword(password)))
            throw new WrongPasswordException();

        return Optional.of(user);
    }

    @Override
    public Optional<User> checkSession(@NonNull String token) {
        return userRepository.findByToken(token);
    }


}
