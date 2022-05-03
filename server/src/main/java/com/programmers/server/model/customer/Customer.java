package com.programmers.server.model.customer;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Customer {
    private final UUID customerId;
    private CustomerInfo customerInfo;
    private final LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

    public Customer(UUID customerId, CustomerInfo customerInfo, LocalDateTime createdAt, LocalDateTime lastLoginAt) {
        this.customerId = customerId;
        this.customerInfo = customerInfo;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
    }

    public void changeCustomerInfo(CustomerInfo customerInfo){
        this.customerInfo = customerInfo;
    }

    public void login(){
        this.lastLoginAt = LocalDateTime.now();
    }
}

