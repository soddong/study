package com.soddong.stdy.ddd.contract.adapter.in.web;

import com.soddong.stdy.ddd.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.ddd.contract.adapter.in.web.dto.ContractRequest;
import com.soddong.stdy.ddd.contract.adapter.in.web.dto.ContractResponse;
import com.soddong.stdy.ddd.contract.domain.model.Contract;
import com.soddong.stdy.ddd.contract.port.in.ContractUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractUseCase contractUseCase;

    // 계약 등록
    @PostMapping
    public ResponseEntity<ContractResponse> register(@RequestBody ContractRequest request) {
        Contract contract = contractUseCase.register(
                request.startDate(),
                request.endDate(),
                request.customerId()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ContractResponse.from(contract));
    }

    // 계약 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> get(@PathVariable Long id) {
        Contract contract = contractUseCase.getContract(id);
        return ResponseEntity.ok(ContractResponse.from(contract));
    }

    // 조건부 전체 계약 조회
    @GetMapping
    public ResponseEntity<List<ContractResponse>> search(@ModelAttribute ContractQuery query) {
        List<Contract> contracts = contractUseCase.searchByCondition(query);
        List<ContractResponse> responses = contracts.stream()
                .map(ContractResponse::from)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
