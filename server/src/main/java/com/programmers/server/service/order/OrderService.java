package com.programmers.server.service.order;

import com.programmers.server.model.order.Order;

import java.util.List;

public interface OrderService {

    void createOrder(Order order);
    List<Order> findOrderList(String Email);
}
