package org.example.fundamentals.sales.infrastructure.persistence;

import org.example.fundamentals.sales.domain.model.Product;
import org.example.fundamentals.sales.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    @PostConstruct
    public void init() {
        // Add some dummy products
        UUID productId1 = UUID.fromString("c8a8b8e0-5b8f-4b6e-8b0e-3b3b3b3b3b3b");
        products.put(productId1, new Product(productId1, new BigDecimal("10.00")));

        UUID productId2 = UUID.fromString("f8a8b8e0-5b8f-4b6e-8b0e-3b3b3b3b3b3b");
        products.put(productId2, new Product(productId2, new BigDecimal("20.00")));
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
