package com.soddong.stdy.querydsl.contract.adapter.in.web.dto;

import com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity.ContractStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ContractQuery(
        ContractStatus status,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate startDateFrom,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate startDateTo
) {}
