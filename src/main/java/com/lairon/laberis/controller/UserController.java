package com.lairon.laberis.controller;

import com.lairon.laberis.domain.User;
import com.lairon.laberis.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User user) {
        try{
            return ResponseEntity.ok(userService.register(user.getLogin(), user.getPassword(), user.getFirstname(), user.getLastname()));
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PostMapping("/login")
    private ResponseEntity<User> login(@RequestBody User user) {
        try{
            Optional<User> login = userService.login(user.getLogin(), user.getPassword());
            if (!login.isPresent())
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(login.orElse(null));

        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatusCode()).build();
        }
    }

    @PostMapping("/checksession")
    private ResponseEntity<User> checkSession(@RequestBody String token){
        User user = userService.checkSession(token).orElse(null);
        if(user == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(user);
    }

}
