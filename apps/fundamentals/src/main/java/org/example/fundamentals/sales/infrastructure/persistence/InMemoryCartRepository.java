package org.example.fundamentals.sales.infrastructure.persistence;

import org.example.fundamentals.sales.domain.model.Cart;
import org.example.fundamentals.sales.domain.repository.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryCartRepository implements CartRepository {

    private final Map<UUID, Cart> carts = new HashMap<>();

    @Override
    public Optional<Cart> findById(UUID id) {
        return Optional.ofNullable(carts.get(id));
    }

    @Override
    public void save(Cart cart) {
        carts.put(cart.getId(), cart);
    }
}
