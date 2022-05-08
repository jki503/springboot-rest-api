package com.programmers.server.repository.order;

import com.programmers.server.model.customer.Customer;
import com.programmers.server.model.customer.CustomerInfo;
import com.programmers.server.model.order.Order;
import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.model.order.OrderStatus;
import com.programmers.server.model.product.Category;
import com.programmers.server.repository.customer.CustomerRepository;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrderJdbcRepositoryTest {

    static EmbeddedMysql embeddedMysql;

    @BeforeAll
    static void set(){
        MysqldConfig config = MysqldConfig.aMysqldConfig(Version.v8_0_11)
                .withCharset(Charset.UTF8)
                .withPort(2215)
                .withUser("test", "test1234!")
                .withTimeZone("Asia/Seoul")
                .build();

        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test-customers", ScriptResolver.classPathScript("schema.sql"))
                .start();
    }

    @AfterAll
    static void cleanup(){
        embeddedMysql.stop();
    }

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderJdbcRepository orderJdbcRepository;

    @Test
    @DisplayName("고객의 주문 리스트를 가져오는지")
    void testOrderListByCustomerId(){
        Customer customer = new Customer(UUID.randomUUID(),new CustomerInfo("jung","jung111@gmail.com"),LocalDateTime.now(),LocalDateTime.now());
        customerRepository.insert(customer);

        orderJdbcRepository.insert(new Order(UUID.randomUUID(),customer.getCustomerId(),"jung111@gmail.com","경기도 구리시","1234",OrderStatus.ACCEPTED,LocalDateTime.now(),LocalDateTime.now()));
        orderJdbcRepository.insert(new Order(UUID.randomUUID(),customer.getCustomerId(),"jung222@gmail.com","경기도 구리시2","2345",OrderStatus.ACCEPTED,LocalDateTime.now(),LocalDateTime.now()));

        List<Order> orderList = orderJdbcRepository.findByCustomerId(customer.getCustomerId());
        assertThat(orderList.size()).isEqualTo(2);
    }

}
