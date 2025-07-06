package com.soddong.stdy.ddd.contract.domain.model;

import com.soddong.stdy.ddd.contract.adapter.out.persistence.entity.ContractStatus;
import com.soddong.stdy.ddd.contract.domain.model.vo.ContractPeriod;
import lombok.Data;
import org.jmolecules.ddd.annotation.AggregateRoot;

@Data
@AggregateRoot
public class Contract {
    private final Long id;
    private ContractPeriod contractPeriod;
    private ContractStatus status;
    private final Long customerId;

    public Contract(Long id, ContractPeriod contractPeriod, ContractStatus status, Long customerId) {
        this.id = id;
        this.contractPeriod = contractPeriod;
        this.status = status;
        this.customerId = customerId;
    }

    public Contract(Long id, ContractPeriod contractPeriod, Long customerId) {
        this.id = id;
        this.contractPeriod = contractPeriod;
        this.status = status;
        this.customerId = customerId;
    }

    // 등록용 생성 팩토리
    public static Contract create(ContractPeriod contractPeriod, Long customerId) {
        return new Contract(null, contractPeriod, ContractStatus.ACTIVE, customerId);
    }

    // 조회용 팩토리
    public static Contract of(Long id, ContractPeriod contractPeriod, ContractStatus status, Long customerId) {
        return new Contract(id, contractPeriod, status, customerId);
    }


    public boolean isExpired() {
        return contractPeriod.isExpired();
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
