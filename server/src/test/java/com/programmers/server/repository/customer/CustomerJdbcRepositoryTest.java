package com.programmers.server.repository.customer;

import com.programmers.server.model.customer.Customer;
import com.programmers.server.model.customer.CustomerInfo;
import com.programmers.server.model.customer.dto.CustomerDto;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v8_0_11;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class CustomerJdbcRepositoryTest {


    static EmbeddedMysql embeddedMysql;

    @BeforeAll
    static void setup(){
        MysqldConfig config = aMysqldConfig(v8_0_11)
                .withCharset(Charset.UTF8)
                .withPort(2215)
                .withUser("test","test1234!")
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

    @Test
    @DisplayName("회원가입이 올바르게 되는지")
    void testJoin(){
        Customer customer = new Customer(UUID.randomUUID(),
                new CustomerInfo("jung", "jung111@gmail.com"),
                LocalDateTime.now(),
                LocalDateTime.now());

        customerRepository.insert(customer);
    }

    @Test
    @DisplayName("이메일로 회원 조회 되는지")
    void testFindByEmail(){
        Customer customer = new Customer(UUID.randomUUID(),
                new CustomerInfo("jung", "jung111@gmail.com"),
                LocalDateTime.now(),
                LocalDateTime.now());

        customerRepository.insert(customer);

        Optional<Customer> maybeCustomer = customerRepository.findByEmail("jung111@gmail.com");
        assertTrue(maybeCustomer.isPresent());
    }

}