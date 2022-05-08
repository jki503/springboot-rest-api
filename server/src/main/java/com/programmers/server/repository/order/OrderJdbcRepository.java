package com.programmers.server.repository.order;

import com.programmers.server.model.order.Order;
import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.model.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static com.programmers.server.repository.JdbcUtils.toUUID;
import static com.programmers.server.repository.JdbcUtils.toLocalDateTime;

import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Order insert(Order order) {
        jdbcTemplate.update("insert into orders(customer_id, order_id, email, address, postcode, order_status, created_at, updated_at) " +
                        "VALUES(UUID_TO_BIN(:customerId), UUID_TO_BIN(:orderId), :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)",
                toOrderParamMap(order));

        return order;
    }

    @Override
    public List<Order> findByCustomerId(UUID customerId) {
        return jdbcTemplate.query("select * from orders where customer_id = UUID_TO_BIN(:customerId)",
                Collections.singletonMap("customerId", customerId.toString().getBytes()),
                orderRowMapper);
    }

    @Override
    public List<Order> findByCustomerEmail(String email) {
        return jdbcTemplate.query("select * from orders where email = :email",
                Collections.singletonMap("email", email),
                orderRowMapper);
    }

    private static final RowMapper<Order> orderRowMapper = (resultSet, i) -> {
        UUID orderId = toUUID(resultSet.getBytes("order_id"));
        UUID customerId = toUUID(resultSet.getBytes("customer_id"));
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String postcode = resultSet.getString("postcode");
        OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

        return new Order(
                orderId,
                customerId,
                email,
                address,
                postcode,
                orderStatus,
                createdAt,
                updatedAt
        );
    };

    private Map<String, Object> toOrderParamMap(Order order) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("customerId", order.getCustomerId().toString().getBytes());
        paramMap.put("email", order.getEmail());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());

        return paramMap;
    }


}
