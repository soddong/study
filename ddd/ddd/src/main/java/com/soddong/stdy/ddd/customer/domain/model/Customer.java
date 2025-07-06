package com.soddong.stdy.ddd.customer.domain.model;

import com.soddong.stdy.ddd.customer.domain.model.vo.PhoneNumber;
import lombok.Data;
import org.jmolecules.ddd.annotation.AggregateRoot;

@Data
@AggregateRoot
public class Customer {

    private final Long id;
    private final String name;
    private final PhoneNumber phone;
    private final boolean vip;

    public Customer(Long id, String name, PhoneNumber phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.vip = phone != null;
    }

    public boolean isVip() {
        return vip;
    }
}
