package com.programmers.server.model.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerInfoTest {

    static String correctName = "jung";
    static String correctEmail = "jung@gmail.com";

    @Test
    @DisplayName("올바르지 않은 이메일을 받을 경우")
    void testInvalidEmail(){
        assertAll(
                () ->  assertThrows(IllegalArgumentException.class , ()->{new CustomerInfo(correctName,"@gmail.com");}),
                () ->  assertThrows(IllegalArgumentException.class , ()->{new CustomerInfo(correctName,"aa@.com");}),
                () ->  assertThrows(IllegalArgumentException.class , ()->{new CustomerInfo(correctName,"a@com");})
                );
    }

    @Test
    @DisplayName("올바른 이메일을 받을 경우")
    void testValidEmail(){
        assertAll(
                () -> assertEquals(new CustomerInfo(correctName, correctEmail).getEmail(),correctEmail),
                () -> assertEquals(new CustomerInfo(correctName,"j@gmail.com").getEmail(),"j@gmail.com")
        );
    }

    @Test
    @DisplayName("고객 이름이 올바르지 않을 경우")
    void testInvalidName(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () ->{new CustomerInfo("",correctEmail);}),
                () -> assertThrows(IllegalArgumentException.class, () ->{new CustomerInfo("aa",correctEmail);}),
                () -> assertThrows(IllegalArgumentException.class, () ->{new CustomerInfo("aaaaaaaaaaaaa",correctEmail);})
        );
    }

    @Test
    @DisplayName("올바른 고객이름을 받을 경우")
    void testInvalidValidName(){
        assertAll(
                () -> assertEquals(new CustomerInfo(correctName, correctEmail).getName(),correctName),
                () -> assertEquals(new CustomerInfo("nana",correctEmail).getName(),"nana")
        );
    }

}