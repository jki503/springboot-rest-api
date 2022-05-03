package com.programmers.server.model.customer.dto;

import com.programmers.server.model.customer.Customer;
import com.programmers.server.model.customer.CustomerInfo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CustomerDto {

    private final String name;
    private final String email;

    public CustomerDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static CustomerDto of(Customer customer){
        CustomerInfo customerInfo = customer.getCustomerInfo();
        return new CustomerDto(customerInfo.getName(), customerInfo.getName());
    }

    public Customer toEntity(){
        return new Customer(UUID.randomUUID(), new CustomerInfo(name, email), LocalDateTime.now(), LocalDateTime.now());
    }

}
