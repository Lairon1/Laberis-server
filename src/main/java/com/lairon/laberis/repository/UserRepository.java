package com.lairon.laberis.repository;

import com.lairon.laberis.domain.User;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByToken(@NonNull String token);
    Optional<User> findByLogin(@NonNull String login);

}
