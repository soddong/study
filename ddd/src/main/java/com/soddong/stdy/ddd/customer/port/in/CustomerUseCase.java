package com.soddong.stdy.ddd.customer.port.in;

import com.soddong.stdy.ddd.customer.adapter.in.web.dto.CustomerQuery;
import com.soddong.stdy.ddd.customer.domain.model.Customer;

import java.util.List;

public interface CustomerUseCase {
    Customer register(String name, String phone);
    Customer getCustomer(Long id);
    List<Customer> getAll(CustomerQuery query);
}
