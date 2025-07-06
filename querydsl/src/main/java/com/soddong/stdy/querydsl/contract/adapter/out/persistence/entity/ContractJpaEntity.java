package com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity;

import com.soddong.stdy.querydsl.contract.domain.model.vo.ContractPeriod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="contract")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ContractJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private ContractPeriod contractPeriod;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    private Long customerId;
}
