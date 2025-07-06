package com.soddong.stdy.ddd.customer.port.out;

import com.soddong.stdy.ddd.customer.adapter.in.web.dto.CustomerQuery;
import com.soddong.stdy.ddd.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(Long id);
    List<Customer> findAll(CustomerQuery query);
}
