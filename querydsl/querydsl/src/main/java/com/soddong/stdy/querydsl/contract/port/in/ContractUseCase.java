package com.soddong.stdy.querydsl.contract.port.in;

import com.soddong.stdy.querydsl.contract.domain.model.Contract;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContractUseCase {
    Contract register(LocalDate localDate, LocalDate localDate1, Long aLong);
    Contract getContract(Long id);
    List<Contract> getAll();
}