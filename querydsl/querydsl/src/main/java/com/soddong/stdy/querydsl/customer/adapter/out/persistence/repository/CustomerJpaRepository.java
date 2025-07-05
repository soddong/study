package com.soddong.stdy.querydsl.customer.adapter.out.persistence.repository;

import com.soddong.stdy.querydsl.customer.adapter.out.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {

}
