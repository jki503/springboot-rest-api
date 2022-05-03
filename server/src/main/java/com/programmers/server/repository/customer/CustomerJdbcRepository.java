package com.programmers.server.repository.customer;

import com.programmers.server.model.customer.Customer;
import com.programmers.server.model.customer.CustomerInfo;
import com.programmers.server.model.customer.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.programmers.server.repository.JdbcUtils.toLocalDateTime;
import static com.programmers.server.repository.JdbcUtils.toUUID;

@Repository
@RequiredArgsConstructor
public class CustomerJdbcRepository implements CustomerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public CustomerDto insert(Customer customer) {
        int update = jdbcTemplate.update("insert into customers(customer_id, name, email, create_at, last_login_at) " +
                "values(UUID_TO_BIN(:customerId), :name, :email, :createdAt, :lastLoginAt)",
                toCustomerParamMap(customer));

        return CustomerDto.of(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            "select * from customers where email = :email",
                            Collections.singletonMap("email", email),
                            customerRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateLastLoginAt(Customer customer) {
        int update = jdbcTemplate.update("update customers set last_login_at = :lastLoginAt where customer_id = UUID_TO_BIN(:customerId)",
                toCustomerParamMap(customer));
        if(update != 1)
            throw new RuntimeException("마지막 로그인 업데이트 실패");
    }

    private static final RowMapper<Customer> customerRowMapper = (resultSet, i) -> {

        UUID customerId = toUUID(resultSet.getBytes("customer_id"));
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("create_at"));
        LocalDateTime lastLoginAt = toLocalDateTime(resultSet.getTimestamp("last_login_at"));

        return new Customer(customerId,new CustomerInfo(name,email), createdAt, lastLoginAt);
    };

    private Map<String, Object> toCustomerParamMap(Customer customer) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("customerId", customer.getCustomerId().toString().getBytes());
        paramMap.put("name", customer.getCustomerInfo().getName());
        paramMap.put("email", customer.getCustomerInfo().getEmail());
        paramMap.put("createdAt", customer.getCreatedAt());
        paramMap.put("lastLoginAt", customer.getLastLoginAt());

        return paramMap;
    }
}
