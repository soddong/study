package com.soddong.stdy.ddd.customer.adapter.out.persistence.repository;

import com.soddong.stdy.ddd.customer.adapter.out.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CustomerJpaRepository extends
        JpaRepository<CustomerJpaEntity, Long>,
        QuerydslPredicateExecutor<CustomerJpaEntity> {

}
