package com.soddong.stdy.querydsl.contract.service;

import com.soddong.stdy.querydsl.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.querydsl.contract.domain.model.Contract;
import com.soddong.stdy.querydsl.contract.domain.model.vo.ContractPeriod;
import com.soddong.stdy.querydsl.contract.port.in.ContractUseCase;
import com.soddong.stdy.querydsl.contract.port.out.ContractRepository;
import com.soddong.stdy.querydsl.customer.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractUseCase {

    private final ContractRepository contractRepository;

    @Override
    public Contract register(LocalDate startDate, LocalDate endDate, Long customerId) {
        ContractPeriod contractPeriod = new ContractPeriod(startDate, endDate);
        return contractRepository.save(
                Contract.create(contractPeriod, customerId)
        );
    }

    @Override
    public Contract getContract(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("계약이 존재하지 않습니다. ID: " + id));
    }

    @Override
    public List<Contract> searchByCondition(ContractQuery query) {
        return contractRepository.searchByCondition(query);
    }
}
