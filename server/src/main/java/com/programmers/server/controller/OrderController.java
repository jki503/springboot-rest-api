package com.programmers.server.controller;

import com.programmers.server.model.order.Order;
import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.model.order.dto.OrderDto;
import com.programmers.server.service.order.OrderProductService;
import com.programmers.server.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderProductService orderProductService;

    @PostMapping("/pay")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto){
        Order order = orderDto.toEntity();
        List<OrderProduct> orderProducts = orderDto.getOrderProducts()
                .stream()
                .map(orderProductDto -> orderProductDto.toEntity(order.getOrderId()))
                .toList();

        orderService.createOrder(order);
        orderProductService.createOrderProducts(orderProducts);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/order-list/{email}")
    public ResponseEntity<List<Order>> findOrderList(@PathVariable String email){
        return ResponseEntity.ok(orderService.findOrderList(email));
    }
}
