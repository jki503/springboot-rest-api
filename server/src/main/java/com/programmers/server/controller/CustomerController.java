package com.programmers.server.controller;

import com.programmers.server.model.customer.dto.CustomerDto;
import com.programmers.server.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/join")
    public ResponseEntity<CustomerDto> joinCustomer(@RequestBody CustomerDto customerDto){
        customerService.joinCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerDto> login(@RequestBody CustomerDto customerDto){
        customerService.login(customerDto);
        return ResponseEntity.ok(customerDto);
    }
}