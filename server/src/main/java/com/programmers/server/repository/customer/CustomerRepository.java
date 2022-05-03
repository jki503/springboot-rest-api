package com.programmers.server.repository.customer;

import com.programmers.server.model.customer.Customer;
import com.programmers.server.model.customer.CustomerInfo;
import com.programmers.server.model.customer.dto.CustomerDto;

import java.util.Optional;
public interface CustomerRepository {

    CustomerDto insert(Customer customer);

    Optional<Customer> findByEmail(String email);

    void updateLastLoginAt(Customer customer);
}
