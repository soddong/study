package com.soddong.stdy.querydsl.contract.adapter.out.persistence.mapper;

import com.soddong.stdy.querydsl.contract.domain.model.Contract;
import com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity.ContractJpaEntity;

public class ContractMapper {

    public static Contract toDomain(ContractJpaEntity entity) {
        if (entity == null) return null;

        return new Contract(
                entity.getId(),
                entity.getContractPeriod(),
                entity.getStatus(),
                entity.getCustomerId()
        );
    }

    public static ContractJpaEntity toEntity(Contract contract) {
        if (contract == null) return null;

        return ContractJpaEntity.builder()
                .id(contract.getId())
                .contractPeriod(contract.getContractPeriod())
                .status(contract.getStatus())
                .customerId(contract.getCustomerId())
                .build();
    }
}
