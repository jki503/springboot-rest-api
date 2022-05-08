package com.programmers.server.repository.order;

import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.model.product.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.programmers.server.repository.JdbcUtils.toLocalDateTime;
import static com.programmers.server.repository.JdbcUtils.toUUID;

@Repository
@RequiredArgsConstructor
public class OrderProductJdbcRepository implements OrderProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public OrderProduct insertOrderProduct(OrderProduct orderProduct) {
        jdbcTemplate.update("insert into order_products(order_id, product_id, category, price, quantity, created_at, updated_at) " +
                        "values(UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :category, :price, :quantity, :createdAt, :updatedAt)",
                toOrderProductParamMap(orderProduct));

        return orderProduct;
    }

    @Override
    public List<OrderProduct> findByOrderId(UUID orderId) {
        return jdbcTemplate.query("select * from order_products where order_id = UUID_TO_BIN(:orderId)",
                Collections.singletonMap("orderId", orderId.toString().getBytes()),
                orderProductRowMapper);
    }

    private final static RowMapper<OrderProduct> orderProductRowMapper = ((resultSet, i) -> {

        UUID orderId = toUUID(resultSet.getBytes("order_id"));
        UUID productId = toUUID(resultSet.getBytes("product_id"));
        Category category = Category.valueOf(resultSet.getString("category"));
        int price = resultSet.getInt("price");
        int quantity = resultSet.getInt("quantity");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

        return new OrderProduct(
                orderId,
                productId,
                category,
                price,
                quantity,
                createdAt,
                updatedAt);
    });

    private Map<String, Object> toOrderProductParamMap(OrderProduct orderProduct) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("orderId", orderProduct.getOrderId().toString().getBytes());
        paramMap.put("productId", orderProduct.getProductId().toString().getBytes());
        paramMap.put("category", orderProduct.getCategory().toString());
        paramMap.put("price", orderProduct.getPrice());
        paramMap.put("quantity", orderProduct.getQuantity());
        paramMap.put("createdAt", orderProduct.getCreatedAt());
        paramMap.put("updatedAt", orderProduct.getUpdatedAt());

        return paramMap;
    }
}
