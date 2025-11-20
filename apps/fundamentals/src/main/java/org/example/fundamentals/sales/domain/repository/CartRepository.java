package org.example.fundamentals.sales.domain.repository;

import org.example.fundamentals.sales.domain.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    Optional<Cart> findById(UUID id);
    void save(Cart cart);
}
