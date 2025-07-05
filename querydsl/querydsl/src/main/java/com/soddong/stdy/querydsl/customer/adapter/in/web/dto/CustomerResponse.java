package com.soddong.stdy.querydsl.customer.adapter.in.web.dto;

import com.soddong.stdy.querydsl.customer.domain.model.Customer;

public record CustomerResponse(Long id, String name, String phone) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getPhone());
    }
}
