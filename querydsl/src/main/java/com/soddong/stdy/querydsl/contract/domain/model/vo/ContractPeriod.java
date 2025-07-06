package com.soddong.stdy.querydsl.contract.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Value;
import org.jmolecules.ddd.annotation.ValueObject;

import java.time.LocalDate;

@Getter
@Embeddable
@ValueObject
public class ContractPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    protected ContractPeriod() { }

    public ContractPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("시작일과 종료일은 필수입니다.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("종료일은 시작일보다 이후여야 합니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isExpired() {
        return endDate.isBefore(LocalDate.now());
    }
}