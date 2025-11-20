package org.example.fundamentals.sales.infrastructure.web;

import org.example.fundamentals.sales.domain.model.Cart;
import org.example.fundamentals.sales.domain.model.Product;
import org.example.fundamentals.sales.domain.repository.CartRepository;
import org.example.fundamentals.sales.domain.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<Void> addItemToCart(@PathVariable UUID cartId, @RequestBody AddItemRequest request) {
        Cart cart = cartRepository.findById(cartId).orElse(new Cart(cartId));
        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new RuntimeException("Product not found"));
        cart.addItem(product, request.quantity());
        cartRepository.save(cart);
        return ResponseEntity.ok().build();
    }

    public record AddItemRequest(UUID productId, int quantity) {
    }
}
