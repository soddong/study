package com.soddong.stdy.querydsl.contract.domain.model.entity;

import com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity.ContractStatus;
import lombok.Getter;
import org.jmolecules.ddd.annotation.Entity;

import java.time.LocalDateTime;

@Getter
@Entity
public class ContractHistory {

    private final Long id;
    private final ContractStatus oldStatus;
    private final ContractStatus newStatus;
    private final LocalDateTime changedAt;

    public ContractHistory(Long id, ContractStatus oldStatus, ContractStatus newStatus, LocalDateTime changedAt) {
        this.id = id;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedAt = changedAt;
    }

    public static ContractHistory of(ContractStatus oldStatus, ContractStatus newStatus) {
        return new ContractHistory(null, oldStatus, newStatus, LocalDateTime.now());
    }
}