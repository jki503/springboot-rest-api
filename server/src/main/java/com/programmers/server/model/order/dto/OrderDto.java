package com.programmers.server.model.order.dto;

import com.programmers.server.model.order.OrderProduct;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class OrderDto {

    private final UUID customerId;
    private final String email;
    private final String address;
    private final String postcode;
    private final List<OrderProduct> orderItems;

    public OrderDto(UUID customerId, String email, String address, String postcode, List<OrderProduct> orderItems) {
        this.customerId = customerId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
    }
}
