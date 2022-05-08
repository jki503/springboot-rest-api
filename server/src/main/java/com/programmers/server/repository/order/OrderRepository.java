package com.programmers.server.repository.order;

import com.programmers.server.model.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order insert(Order order);

    List<Order> findByCustomerId(UUID customerId);

    List<Order> findByCustomerEmail(String email);
}
