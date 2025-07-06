package com.soddong.stdy.querydsl.customer.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

@Getter
@Embeddable
@ValueObject
public class PhoneNumber {

    private String number;

    protected PhoneNumber() { }

    public PhoneNumber(String number) {
        if (!number.matches("^0\\d{1,2}-\\d{3,4}-\\d{4}$")) {
            throw new IllegalArgumentException("유효하지 않은 전화번호 형식입니다: " + number);
        }
        this.number = number;
    }
}

