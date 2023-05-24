package com.lairon.laberis.repository;

import com.lairon.laberis.domain.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, String> {

    Optional<Cart> getCartByOwnerLogin(String login);

}
