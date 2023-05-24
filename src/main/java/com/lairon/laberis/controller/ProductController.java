package com.lairon.laberis.controller;

import com.lairon.laberis.domain.Product;
import com.lairon.laberis.domain.User;
import com.lairon.laberis.domain.UserRole;
import com.lairon.laberis.repository.ProductRepository;
import com.lairon.laberis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product, @RequestParam String token) {
        User user = userRepository.findByToken(token).orElse(null);
        if(user == null || user.getRole() == UserRole.USER)
            return ResponseEntity.status(600).build();

        product = productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/delete")
    public ResponseEntity deleteProduct(@RequestParam String token, @RequestParam long id){
        User user = userRepository.findByToken(token).orElse(null);
        if(user == null || user.getRole() == UserRole.USER)
            return ResponseEntity.status(600).build();
        Product product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();
        productRepository.delete(product);
        return ResponseEntity.ok().build();

    }

}
