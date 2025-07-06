package com.soddong.stdy.ddd.contract.adapter.in.web.dto;

import com.soddong.stdy.ddd.contract.adapter.out.persistence.entity.ContractStatus;
import com.soddong.stdy.ddd.contract.domain.model.Contract;

import java.time.LocalDate;

public record ContractResponse(
        Long id,
        LocalDate startDate,
        LocalDate endDate,
        ContractStatus status,
        Long customerId
) {
    public static ContractResponse from(Contract contract) {
        return new ContractResponse(
                contract.getId(),
                contract.getContractPeriod().getStartDate(),
                contract.getContractPeriod().getEndDate(),
                contract.getStatus(),
                contract.getCustomerId()
        );
    }
}