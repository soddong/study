package com.soddong.stdy.querydsl.customer.port.in;

import com.soddong.stdy.querydsl.customer.domain.model.Customer;

import java.util.List;

public interface CustomerUseCase {
    Customer register(String name, String phone);
    Customer getCustomer(Long id);
    List<Customer> getAll();
}
