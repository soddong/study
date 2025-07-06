package com.soddong.stdy.ddd.customer.adapter.in.web.dto;

import com.soddong.stdy.ddd.customer.domain.model.Customer;

public record CustomerResponse(Long id, String name, String phone) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getPhone().getNumber());
    }
}
