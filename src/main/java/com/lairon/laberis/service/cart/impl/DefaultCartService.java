package com.lairon.laberis.service.cart.impl;

import com.lairon.laberis.domain.Cart;
import com.lairon.laberis.domain.Product;
import com.lairon.laberis.service.cart.CartService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class DefaultCartService implements CartService {

    @Override
    public void recalculateCart(@NonNull Cart cart) {
        double finalCost = 0;
        for (Product product : cart.getProducts()) {
            double price = product.getPrice();
            finalCost += price;
        }
        cart.setFinalCost(finalCost);
    }


}

