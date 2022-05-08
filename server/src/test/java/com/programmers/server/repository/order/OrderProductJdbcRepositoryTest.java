package com.programmers.server.repository.order;

import com.programmers.server.model.customer.Customer;
import com.programmers.server.model.customer.CustomerInfo;
import com.programmers.server.model.order.Order;
import com.programmers.server.model.order.OrderProduct;
import com.programmers.server.model.order.OrderStatus;
import com.programmers.server.model.product.Category;
import com.programmers.server.model.product.Product;
import com.programmers.server.repository.customer.CustomerRepository;
import com.programmers.server.repository.product.ProductRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrderProductJdbcRepositoryTest {

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
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Test
    @DisplayName("주문의 상품 리스트를 가져오는지 테스트")
    void testOrderProductByOrderId(){

        Customer customer = new Customer(UUID.randomUUID(),new CustomerInfo("jung","jung111@gmail.com"), LocalDateTime.now(),LocalDateTime.now());
        customerRepository.insert(customer);

        Order order = new Order(UUID.randomUUID(),customer.getCustomerId(),"jung111@gmail.com","경기도 구리시","1234", OrderStatus.ACCEPTED,LocalDateTime.now(),LocalDateTime.now());
        orderRepository.insert(order);

        Product product = new Product(UUID.randomUUID(),"sample1", Category.COFFEE_BEAN_PACKAGE,3000,"sample1",LocalDateTime.now(),LocalDateTime.now());
        productRepository.insert(product);

        OrderProduct orderProduct = new OrderProduct(order.getOrderId(), product.getProductId(), Category.COFFEE_BEAN_PACKAGE, product.getPrice() * 3, 3,order.getCreatedAt(), order.getUpdatedAt());
        OrderProduct orderProduct2 = new OrderProduct(order.getOrderId(), product.getProductId(), Category.COFFEE_BEAN_PACKAGE, product.getPrice() * 4, 4,order.getCreatedAt(), order.getUpdatedAt());

        orderProductRepository.insertOrderProduct(orderProduct);
        orderProductRepository.insertOrderProduct(orderProduct2);

        assertThat(orderProductRepository.findByOrderId(order.getOrderId()).size()).isEqualTo(2);
    }

}