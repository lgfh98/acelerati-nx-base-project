package org.example.fundamentals.sales.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<CartItem> items;
    private final BigDecimal total;
    private OrderStatus status;

    public Order(Cart cart) {
        this.id = UUID.randomUUID();
        this.items = cart.getItems();
        this.total = cart.getTotal();
        this.status = OrderStatus.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
