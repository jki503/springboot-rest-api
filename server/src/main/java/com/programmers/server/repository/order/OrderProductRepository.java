package com.programmers.server.repository.order;

import com.programmers.server.model.order.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepository {

    OrderProduct insertOrderProduct(OrderProduct orderProduct);

    List<OrderProduct> findByOrderId(UUID orderId);

}
