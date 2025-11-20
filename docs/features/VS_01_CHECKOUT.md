# Vertical Slice: Checkout & Order Creation

**Status:** Implemented

**Date:** 2025-11-20

**1. Summary**
Implementation of the Cart to Order conversion flow following DDD and Hexagonal Architecture. This feature allows converting a shopping cart into an order through a REST endpoint.

**2. Component Structure (Final)**

**Modified Files:**
- `apps/fundamentals/build.gradle.kts`
- `apps/fundamentals/src/main/java/org/example/fundamentals/FundamentalsApplication.java`

**Created Files:**
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/model/Cart.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/model/CartItem.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/model/Order.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/model/OrderStatus.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/model/Product.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/model/EmptyCartException.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/repository/CartRepository.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/repository/OrderRepository.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/domain/repository/ProductRepository.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/application/usecase/CheckoutUseCase.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/infrastructure/persistence/InMemoryCartRepository.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/infrastructure/persistence/InMemoryOrderRepository.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/infrastructure/persistence/InMemoryProductRepository.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/infrastructure/web/CheckoutController.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/infrastructure/web/CartController.java`
- `apps/fundamentals/src/main/java/org/example/fundamentals/sales/infrastructure/SalesConfiguration.java`

**3. Class Diagram (Mermaid)**

```mermaid
classDiagram
    class CheckoutController {
        +checkout(cartId)
    }

    class CheckoutUseCase {
        +checkout(cartId)
    }

    class CartRepository {
        <<interface>>
        +findById(id)
        +save(cart)
    }

    class OrderRepository {
        <<interface>>
        +save(order)
    }

    class InMemoryCartRepository {
        +findById(id)
        +save(cart)
    }

    class InMemoryOrderRepository {
        +save(order)
    }

    class Cart {
        -id: UUID
        -items: List~CartItem~
        +addItem(product, quantity)
        +getTotal()
        +isEmpty()
    }

    class Order {
        -id: UUID
        -items: List~CartItem~
        -total: BigDecimal
        -status: OrderStatus
    }

    class CartItem {
        -product: Product
        -quantity: int
    }

    class Product {
        -id: UUID
        -price: BigDecimal
    }

    enum OrderStatus {
        PENDING,
        COMPLETED,
        CANCELLED
    }

    CheckoutController ..> CheckoutUseCase
    CheckoutUseCase ..> CartRepository
    CheckoutUseCase ..> OrderRepository
    InMemoryCartRepository --|> CartRepository
    InMemoryOrderRepository --|> OrderRepository
    CheckoutUseCase ..> Cart
    Order --o Cart
    Cart *-- "1..*" CartItem
    CartItem o-- Product
```

**4. Design Decisions**
The implementation follows a classic Hexagonal Architecture (Ports and Adapters) approach to separate the core business logic (domain) from application-specific details and external infrastructure.

- **Domain:** The `domain` package is pure business logic, with no dependencies on any framework. It contains the aggregates (`Cart`, `Order`), value objects (`CartItem`), and repository interfaces (ports).
- **Application:** The `application` package orchestrates the domain logic. The `CheckoutUseCase` contains the high-level flow of the checkout process.
- **Infrastructure:** The `infrastructure` package provides concrete implementations (adapters) for the ports defined in the domain. This includes in-memory repositories for persistence and REST controllers for the web interface. This separation allows for easy replacement of infrastructure components without affecting the core domain logic. For example, the in-memory repositories could be swapped with implementations that use a real database.
- **Dependency Injection:** Spring is used to manage dependencies and wire the components together, but the core domain is completely unaware of it.
