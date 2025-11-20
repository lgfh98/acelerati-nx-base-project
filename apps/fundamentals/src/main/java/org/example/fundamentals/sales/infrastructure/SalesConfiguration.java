package org.example.fundamentals.sales.infrastructure;

import org.example.fundamentals.sales.application.usecase.CheckoutUseCase;
import org.example.fundamentals.sales.domain.repository.CartRepository;
import org.example.fundamentals.sales.domain.repository.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalesConfiguration {

    @Bean
    public CheckoutUseCase checkoutUseCase(CartRepository cartRepository, OrderRepository orderRepository) {
        return new CheckoutUseCase(cartRepository, orderRepository);
    }
}
