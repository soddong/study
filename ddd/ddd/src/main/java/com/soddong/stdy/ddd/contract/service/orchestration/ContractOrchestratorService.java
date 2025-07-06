package com.soddong.stdy.ddd.contract.service.orchestration;

import com.soddong.stdy.ddd.contract.domain.model.Contract;
import com.soddong.stdy.ddd.contract.domain.model.service.ContractDomainService;
import com.soddong.stdy.ddd.contract.domain.model.vo.ContractPeriod;
import com.soddong.stdy.ddd.contract.port.out.ContractRepository;
import com.soddong.stdy.ddd.customer.domain.model.Customer;
import com.soddong.stdy.ddd.customer.domain.model.exception.CustomerNotFoundException;
import com.soddong.stdy.ddd.customer.port.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ContractOrchestratorService {

    private final CustomerRepository customerRepository;
    private final ContractDomainService contractDomainService;
    private final ContractRepository contractRepository;

    public Contract register(LocalDate startDate, LocalDate endDate, Long customerId) {

        // 1. 고객 존재 여부 확인 (Application layer 책임)
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        // 2. 계약 기간 생성
        ContractPeriod contractPeriod = new ContractPeriod(startDate, endDate);

        // 3. 도메인 정책에 따라 계약 생성 (도메인 로직 분리)
        Contract contract = contractDomainService.createContract(customerId, contractPeriod);

        // 4. 저장 (Repository 책임)
        return contractRepository.save(contract);
    }
}