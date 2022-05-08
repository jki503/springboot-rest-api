package com.programmers.server.service.order;

import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.repository.order.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService{

    private final OrderProductRepository orderProductRepository;

    @Transactional
    @Override
    public void createOrderProducts(List<OrderProduct> orderProducts) {
        orderProducts.forEach(orderProductRepository::insertOrderProduct);
    }

}
