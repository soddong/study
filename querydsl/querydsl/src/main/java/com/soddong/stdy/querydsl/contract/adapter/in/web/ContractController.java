package com.soddong.stdy.querydsl.contract.adapter.in.web;

import com.soddong.stdy.querydsl.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.querydsl.contract.adapter.in.web.dto.ContractRequest;
import com.soddong.stdy.querydsl.contract.adapter.in.web.dto.ContractResponse;
import com.soddong.stdy.querydsl.contract.domain.model.Contract;
import com.soddong.stdy.querydsl.contract.port.in.ContractUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractUseCase contractUseCase;

    // 계약 등록
    @PostMapping
    public ContractResponse register(@RequestBody ContractRequest request) {
        Contract contract = contractUseCase.register(
                request.startDate(),
                request.endDate(),
                request.customerId()
        );
        return ContractResponse.from(contract);
    }

    // 계약 단건 조회
    @GetMapping("/{id}")
    public ContractResponse get(@PathVariable Long id) {
        return ContractResponse.from(
                contractUseCase.getContract(id)
        );
    }

    // 조건부 전체 계약 조회
    @GetMapping("/contracts")
    public List<ContractResponse> search(@ModelAttribute ContractQuery query) {
        return contractUseCase.searchByCondition(query).stream()
                .map(ContractResponse::from)
                .toList();
    }
}
