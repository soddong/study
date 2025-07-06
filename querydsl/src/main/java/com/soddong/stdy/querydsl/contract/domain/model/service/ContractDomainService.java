package com.soddong.stdy.querydsl.contract.domain.model.service;

import com.soddong.stdy.querydsl.contract.domain.model.Contract;
import com.soddong.stdy.querydsl.contract.domain.model.vo.ContractPeriod;
import com.soddong.stdy.querydsl.contract.port.out.ContractRepository;

@org.springframework.stereotype.Service
@org.jmolecules.ddd.annotation.Service
public class ContractDomainService {

    private final ContractRepository contractRepository;

    public ContractDomainService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(Long customerId, ContractPeriod period) {
        boolean hasContract = contractRepository.existsByCustomerId(customerId);
        if (hasContract) {
            throw new IllegalStateException("고객은 이미 계약을 가지고 있습니다.");
        }

        return Contract.create(period, customerId);
    }
}
