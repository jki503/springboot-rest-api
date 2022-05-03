package com.programmers.server.service.customer;

import com.programmers.server.model.customer.dto.CustomerDto;
import com.programmers.server.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public void joinCustomer(CustomerDto customerDto) {
        customerRepository.findByEmail(customerDto.getEmail())
                .ifPresent(customer -> {
                    log.info("이미 존재하는 회원입니다.");
                });
        customerRepository.insert(customerDto.toEntity());
    }

    @Override
    @Transactional
    public void login(CustomerDto customerDto) {
        customerRepository.findByEmail(customerDto.getEmail())
                .ifPresentOrElse(customer -> {
                            customer.login();
                            customerRepository.updateLastLoginAt(customer);
                        },
                        () -> {
                            throw new RuntimeException("없는 회원입니다.");
                        }
                );
    }
}
