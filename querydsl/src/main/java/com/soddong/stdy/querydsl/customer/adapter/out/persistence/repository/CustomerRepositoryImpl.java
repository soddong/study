package com.soddong.stdy.querydsl.customer.adapter.out.persistence.repository;

import com.soddong.stdy.querydsl.customer.adapter.out.persistence.entity.CustomerJpaEntity;
import com.soddong.stdy.querydsl.customer.adapter.out.persistence.mapper.CustomerMapper;
import com.soddong.stdy.querydsl.customer.domain.model.Customer;
import com.soddong.stdy.querydsl.customer.port.out.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity savedCustomer = customerJpaRepository.save(
                CustomerMapper.toEntity(customer)
        );
        return CustomerMapper.toDomain(savedCustomer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id)
                .map(CustomerMapper::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return customerJpaRepository.findAll()
                .stream().map(CustomerMapper::toDomain)
                .toList();
    }
}