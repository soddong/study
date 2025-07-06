package com.soddong.stdy.ddd.contract.port.in;

import com.soddong.stdy.ddd.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.ddd.contract.domain.model.Contract;

import java.time.LocalDate;
import java.util.List;

public interface ContractUseCase {
    Contract register(LocalDate localDate, LocalDate localDate1, Long aLong);
    Contract getContract(Long id);
    List<Contract> searchByCondition(ContractQuery query);
}