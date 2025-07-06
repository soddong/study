package com.soddong.stdy.ddd.contract.port.out;

import com.soddong.stdy.ddd.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.ddd.contract.domain.model.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractRepository {
    Contract save(Contract contract);
    Optional<Contract> findById(Long id);
    List<Contract> findByCustomerId(Long customerId);
    List<Contract> searchByCondition(ContractQuery query);
    boolean existsByCustomerId(Long customerId);
}
