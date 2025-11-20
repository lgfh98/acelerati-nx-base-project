package org.example.fundamentals.sales.domain.repository;

import org.example.fundamentals.sales.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findById(UUID id);
}
