package com.programmers.server.model.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    @DisplayName("마지막 로그인 업데이트 되는지")
    void testLogin() {
        Customer customer = new Customer(
                UUID.randomUUID(),
                new CustomerInfo("jung", "jung@gmail.com"),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        LocalDateTime beforeLastLoginAt = customer.getLastLoginAt();
        assertSame(customer.getLastLoginAt(),beforeLastLoginAt);

        customer.login();
        assertNotSame(customer.getLastLoginAt(), beforeLastLoginAt);
    }
}