package com.programmers.server.model.order.dto;

import com.programmers.server.model.order.Order;
import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.model.order.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
public class OrderDto {

    private final UUID customerId;
    private final String email;
    private final String address;
    private final String postcode;
    private final List<OrderProductDto> orderProducts;

    public OrderDto(UUID customerId, String email, String address, String postcode, List<OrderProductDto> orderProducts) {
        this.customerId = customerId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderProducts = orderProducts;
    }

    public Order toEntity(){
        return new Order(UUID.randomUUID(),
                customerId,
                email,
                address,
                postcode,
                OrderStatus.ACCEPTED,
                LocalDateTime.now(),
                LocalDateTime.now());
    }
}
