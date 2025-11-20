package org.example.fundamentals.sales.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final BigDecimal price;

    public Product(UUID id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
