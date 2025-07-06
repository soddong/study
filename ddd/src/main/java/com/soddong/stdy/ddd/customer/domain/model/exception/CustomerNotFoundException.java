package com.soddong.stdy.ddd.customer.domain.model.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("고객이 존재하지 않습니다. ID: " + customerId);
    }
}