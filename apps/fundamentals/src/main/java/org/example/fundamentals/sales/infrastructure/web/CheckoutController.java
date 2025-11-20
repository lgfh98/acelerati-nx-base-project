package org.example.fundamentals.sales.infrastructure.web;

import org.example.fundamentals.sales.application.usecase.CheckoutUseCase;
import org.example.fundamentals.sales.domain.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutUseCase checkoutUseCase;

    public CheckoutController(CheckoutUseCase checkoutUseCase) {
        this.checkoutUseCase = checkoutUseCase;
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<Order> checkout(@PathVariable UUID cartId) {
        Order order = checkoutUseCase.checkout(cartId);
        return ResponseEntity.ok(order);
    }
}
