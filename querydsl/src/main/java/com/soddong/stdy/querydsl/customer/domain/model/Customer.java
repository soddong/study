package com.soddong.stdy.querydsl.customer.domain.model;

import lombok.Data;

@Data
public class Customer {

    private final Long id;
    private final String name;
    private final String phone;
    private final boolean vip;

    public Customer(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.vip = phone != null;
    }

    public boolean isVip() {
        return vip;
    }
}
