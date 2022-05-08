package com.programmers.server.service.order;

import com.programmers.server.model.order.Order;
import com.programmers.server.model.order.dto.OrderDto;
import com.programmers.server.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void createOrder(Order order) {
        orderRepository.insert(order);
    }

    @Transactional
    @Override
    public List<Order> findOrderList(String email) {
        return orderRepository.findByCustomerEmail(email);
    }
}
