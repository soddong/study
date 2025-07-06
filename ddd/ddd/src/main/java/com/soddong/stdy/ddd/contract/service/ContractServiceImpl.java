package com.soddong.stdy.ddd.contract.service;

import com.soddong.stdy.ddd.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.ddd.contract.domain.model.Contract;
import com.soddong.stdy.ddd.contract.domain.model.exception.ContractNotFoundException;
import com.soddong.stdy.ddd.contract.domain.model.vo.ContractPeriod;
import com.soddong.stdy.ddd.contract.port.in.ContractUseCase;
import com.soddong.stdy.ddd.contract.port.out.ContractRepository;
import com.soddong.stdy.ddd.contract.service.orchestration.ContractOrchestratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractUseCase {

    private final ContractRepository contractRepository;
    private final ContractOrchestratorService contractOrchestratorService;

    @Override
    public Contract register(LocalDate startDate, LocalDate endDate, Long customerId) {
        return contractOrchestratorService.register(startDate, endDate, customerId);
    }

    @Override
    public Contract getContract(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException(id));
    }

    @Override
    public List<Contract> searchByCondition(ContractQuery query) {
        return contractRepository.searchByCondition(query);
    }
}
