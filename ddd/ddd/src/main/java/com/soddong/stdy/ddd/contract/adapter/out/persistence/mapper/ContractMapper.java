package com.soddong.stdy.ddd.contract.adapter.out.persistence.mapper;

import com.soddong.stdy.ddd.contract.adapter.out.persistence.entity.ContractJpaEntity;
import com.soddong.stdy.ddd.contract.domain.model.Contract;

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
