package com.programmers.server.model.order;

import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
public class Order{

    private final UUID orderId;
    private String email;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private final List<OrderItem> orderItems;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, String email, String address, String postcode, OrderStatus orderStatus, List<OrderItem> orderItems, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateEmail(email);
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setEmail(String email){
        validateEmail(email);
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
        this.updatedAt = LocalDateTime.now();
    }

    public void setOrderStatus(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
        this.updatedAt = LocalDateTime.now();
    }

    private void validateEmail(String email) {
        Assert.notNull(email, "address should not be null");
        Assert.isTrue(email.length() >= 4 && email.length() <=50,
                "address length must be between 4 and 50 characters");
        Assert.isTrue(checkAddress(email), "Invalid email address");
    }

    private static boolean checkAddress(String address){
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b",address);
    }
}
