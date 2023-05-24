package com.lairon.laberis.service.cart;

import com.lairon.laberis.domain.Cart;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    void recalculateCart(@NonNull Cart cart);

}
