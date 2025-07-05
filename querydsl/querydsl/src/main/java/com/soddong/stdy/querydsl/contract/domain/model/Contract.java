package com.soddong.stdy.querydsl.contract.domain.model;

import com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity.ContractStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Contract {
    private final Long id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private ContractStatus status;
    private final Long customerId;

    public Contract(Long id, LocalDate startDate, LocalDate endDate, ContractStatus status, Long customerId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.customerId = customerId;
    }

    public Contract(Long id, LocalDate startDate, LocalDate endDate, Long customerId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.customerId = customerId;
    }

    // 등록용 생성 팩토리
    public static Contract create(LocalDate startDate, LocalDate endDate, Long customerId) {
        return new Contract(null, startDate, endDate, ContractStatus.ACTIVE, customerId);
    }

    // 조회용 팩토리
    public static Contract of(Long id, LocalDate startDate, LocalDate endDate, ContractStatus status, Long customerId) {
        return new Contract(id, startDate, endDate, status, customerId);
    }


    public boolean isExpired() {
        return endDate != null && endDate.isBefore(LocalDate.now());
    }

    public boolean isActive() {
        return status == ContractStatus.ACTIVE;
    }

    public void cancel() {
        if (isExpired()) {
            throw new IllegalStateException("이미 만료된 계약은 취소할 수 없습니다.");
        }
        this.status = ContractStatus.CANCELLED;
    }

    public void activate() {
        if (isExpired()) {
            throw new IllegalStateException("만료된 계약은 활성화할 수 없습니다.");
        }
        this.status = ContractStatus.ACTIVE;
    }
}
