package org.example.fundamentals.sales.application.usecase;

import org.example.fundamentals.sales.domain.model.Cart;
import org.example.fundamentals.sales.domain.model.EmptyCartException;
import org.example.fundamentals.sales.domain.model.Order;
import org.example.fundamentals.sales.domain.repository.CartRepository;
import org.example.fundamentals.sales.domain.repository.OrderRepository;

import java.util.UUID;

public class CheckoutUseCase {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public CheckoutUseCase(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    public Order checkout(UUID cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.isEmpty()) {
            throw new EmptyCartException("Cannot checkout with an empty cart");
        }

        Order order = new Order(cart);
        orderRepository.save(order);

        // Here you would typically publish an EV_OrdCreated event
        // For simplicity, we are skipping the event bus implementation in this slice

        return order;
    }
}
