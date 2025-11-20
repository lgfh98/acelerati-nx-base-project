package org.example.fundamentals.sales.domain.repository;

import org.example.fundamentals.sales.domain.model.Order;

import java.util.UUID;

public interface OrderRepository {
    void save(Order order);
}
