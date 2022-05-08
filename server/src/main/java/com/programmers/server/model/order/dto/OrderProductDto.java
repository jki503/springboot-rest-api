package com.programmers.server.model.order.dto;

import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.model.product.Category;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrderProductDto {
    private final UUID productId;
    private final Category category;
    private final int price;
    private final int quantity;

    public OrderProductDto(UUID productId, Category category, int price, int quantity) {
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderProduct toEntity(UUID orderId){
        return new OrderProduct(
                orderId,
                productId,
                category,
                price,
                quantity,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
