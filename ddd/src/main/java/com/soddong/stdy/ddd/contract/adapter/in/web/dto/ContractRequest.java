package com.soddong.stdy.ddd.contract.adapter.in.web.dto;

import java.time.LocalDate;

public record ContractRequest(
        LocalDate startDate,
        LocalDate endDate,
        Long customerId
) {}