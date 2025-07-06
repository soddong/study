package com.soddong.stdy.ddd.contract.adapter.out.persistence.repository;

import com.soddong.stdy.ddd.contract.adapter.out.persistence.entity.ContractJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractJpaRepository extends JpaRepository<ContractJpaEntity, Long>,
        ContractJpaRepositoryCustom {
    boolean existsByCustomerId(Long customerId);
}