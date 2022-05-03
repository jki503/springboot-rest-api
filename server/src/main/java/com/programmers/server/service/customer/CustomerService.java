package com.programmers.server.service.customer;


import com.programmers.server.model.customer.dto.CustomerDto;

public interface CustomerService {

    void joinCustomer(CustomerDto customerDto);
    void login(CustomerDto customerDto);
}
