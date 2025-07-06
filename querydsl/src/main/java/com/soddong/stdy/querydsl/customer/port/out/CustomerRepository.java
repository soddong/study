package com.soddong.stdy.querydsl.customer.port.out;

import com.soddong.stdy.querydsl.customer.adapter.in.web.dto.CustomerQuery;
import com.soddong.stdy.querydsl.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll(CustomerQuery query);
}
