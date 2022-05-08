package com.programmers.server.model.order;

import com.programmers.server.model.product.Category;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrderProduct {

    private final UUID orderId;
    private final UUID productId;
    private final Category category;
    private final int price;
    private final int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderProduct(UUID orderId, UUID productId, Category category, int price, int quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
