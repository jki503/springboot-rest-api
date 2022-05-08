package com.programmers.server.service.order;

import com.programmers.server.model.order.OrderProduct;

import java.util.List;

public interface OrderProductService {
    void createOrderProducts(List<OrderProduct> orderProducts);
}
