package org.example.fundamentals.sales.infrastructure.persistence;

import org.example.fundamentals.sales.domain.model.Order;
import org.example.fundamentals.sales.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<UUID, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }
}
