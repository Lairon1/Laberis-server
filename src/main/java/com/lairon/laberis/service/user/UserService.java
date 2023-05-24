package com.lairon.laberis.service.user;

import com.lairon.laberis.domain.User;
import com.lairon.laberis.service.password.exception.PasswordException;
import com.lairon.laberis.service.user.exception.UserException;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    User register(
            @NonNull String login,
            @NonNull String password,
            @NonNull String firstname,
            @NonNull String lastname
    ) throws UserException, PasswordException;

    Optional<User> login(
            @NonNull String login,
            @NonNull String password
    ) throws UserException;

    Optional<User> checkSession(@NonNull String token);


}
