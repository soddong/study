package com.soddong.stdy.querydsl.contract.adapter.out.persistence.repository;

import com.soddong.stdy.querydsl.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity.ContractJpaEntity;

import java.util.List;

public interface ContractJpaRepositoryCustom {
    List<ContractJpaEntity> searchByCondition(ContractQuery query);
}
