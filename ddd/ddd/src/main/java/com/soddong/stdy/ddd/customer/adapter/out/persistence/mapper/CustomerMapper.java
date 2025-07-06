package com.soddong.stdy.ddd.customer.adapter.out.persistence.mapper;

import com.soddong.stdy.ddd.customer.adapter.out.persistence.entity.CustomerJpaEntity;
import com.soddong.stdy.ddd.customer.domain.model.Customer;

public class CustomerMapper {

    public static Customer toDomain(CustomerJpaEntity entity) {
        if (entity == null) return null;
        return new Customer(
                entity.getId(),
                entity.getName(),
                entity.getPhone()
        );
    }

    public static CustomerJpaEntity toEntity(Customer domain) {
        if (domain == null) return null;
        return CustomerJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .phone(domain.getPhone())
                .build();
    }
}