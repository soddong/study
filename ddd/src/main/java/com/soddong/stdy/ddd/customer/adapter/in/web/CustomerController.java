package com.soddong.stdy.ddd.customer.adapter.in.web;

import com.soddong.stdy.ddd.customer.adapter.in.web.dto.CustomerQuery;
import com.soddong.stdy.ddd.customer.adapter.in.web.dto.CustomerRequest;
import com.soddong.stdy.ddd.customer.adapter.in.web.dto.CustomerResponse;
import com.soddong.stdy.ddd.customer.port.in.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    // 고객 등록
    @PostMapping
    public CustomerResponse register(@RequestBody CustomerRequest request) {
        return CustomerResponse.from(
                customerUseCase.register(request.name(), request.phone())
        );
    }

    // 단일 고객 조회
    @GetMapping("/{id}")
    public CustomerResponse get(@PathVariable Long id) {
        return CustomerResponse.from(
                customerUseCase.getCustomer(id)
        );
    }

    // 전체 고객 조회
    @GetMapping
    public List<CustomerResponse> getAll(CustomerQuery query) {
        return customerUseCase.getAll(query).stream()
                .map(CustomerResponse::from)
                .toList();
    }

}
