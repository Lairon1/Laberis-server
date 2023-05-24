package com.lairon.laberis.service.password;

import com.lairon.laberis.service.password.exception.PasswordException;
import com.lairon.laberis.service.password.exception.SimplePasswordException;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {

    boolean checkPassword(@NonNull String password) throws PasswordException;

    String hashPassword(@NonNull String password);

}
