package com.soddong.stdy.ddd.contract.domain.model.service;

import com.soddong.stdy.ddd.contract.domain.model.Contract;
import com.soddong.stdy.ddd.contract.domain.model.exception.ContractAlreadyExistsException;
import com.soddong.stdy.ddd.contract.domain.model.vo.ContractPeriod;
import com.soddong.stdy.ddd.contract.port.out.ContractRepository;

@org.springframework.stereotype.Service
@org.jmolecules.ddd.annotation.Service
public class ContractDomainService {

    private final ContractRepository contractRepository;

    public ContractDomainService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(Long customerId, ContractPeriod period) {
        if (contractRepository.existsByCustomerId(customerId)) {
            throw new ContractAlreadyExistsException();
        }
        return Contract.create(period, customerId);
    }
}
