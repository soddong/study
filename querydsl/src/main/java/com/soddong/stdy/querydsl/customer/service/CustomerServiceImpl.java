package com.soddong.stdy.querydsl.customer.service;

import com.soddong.stdy.querydsl.customer.adapter.in.web.dto.CustomerQuery;
import com.soddong.stdy.querydsl.customer.domain.model.Customer;
import com.soddong.stdy.querydsl.customer.port.in.CustomerUseCase;
import com.soddong.stdy.querydsl.customer.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Customer register(String name, String phone) {
        return customerRepository.save(new Customer(null, name, phone));
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("고객을 찾을 수 없습니다. id = " + id));
    }

    @Override
    public List<Customer> getAll(CustomerQuery query) {
        return customerRepository.findAll(query);
    }
}