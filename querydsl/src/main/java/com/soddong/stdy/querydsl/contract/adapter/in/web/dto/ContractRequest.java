package com.soddong.stdy.querydsl.contract.adapter.in.web.dto;

import java.time.LocalDate;

public record ContractRequest(
        LocalDate startDate,
        LocalDate endDate,
        Long customerId
) {}