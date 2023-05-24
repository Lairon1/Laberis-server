package com.lairon.laberis.controller;

import com.lairon.laberis.domain.Cart;
import com.lairon.laberis.domain.Product;
import com.lairon.laberis.domain.User;
import com.lairon.laberis.repository.CartRepository;
import com.lairon.laberis.repository.ProductRepository;
import com.lairon.laberis.repository.UserRepository;
import com.lairon.laberis.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCarts(@RequestHeader("Token") String token) {
        Optional<User> userOpt = userRepository.findByToken(token);
        if (!userOpt.isPresent())
            return ResponseEntity.notFound().build();
        User user = userOpt.get();
        return ResponseEntity.ok(cartRepository.getCartByOwnerLogin(user.getLogin()).orElse(new Cart()));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addProduct(@RequestHeader("Token") String token, @RequestBody List<Long> productsIds){
        Optional<User> userOpt = userRepository.findByToken(token);
        if(!userOpt.isPresent())
            return ResponseEntity.notFound().build();
        User user = userOpt.get();

        Cart cart = cartRepository.getCartByOwnerLogin(user.getLogin()).orElse(new Cart());
        
        for (Product product : productRepository.findAllById(productsIds)) {
            cart.getProducts().add(product);
        }

        cartService.recalculateCart(cart);
        cartRepository.save(cart);
        return ResponseEntity.ok(cart);
    }



}
