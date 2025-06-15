package com.skt.nova.basic.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Account {
    private Long id;

    private Integer balance;

    @Override
    public String toString() {
        return id + "," + balance;
    }

    public Account withBalance(Integer newBalance) {
        return new Account(this.id, newBalance);
    }
}
