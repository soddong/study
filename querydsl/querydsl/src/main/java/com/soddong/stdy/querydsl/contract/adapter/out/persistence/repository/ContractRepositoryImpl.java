package com.soddong.stdy.querydsl.contract.adapter.out.persistence.repository;

import com.soddong.stdy.querydsl.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.querydsl.contract.adapter.out.persistence.mapper.ContractMapper;
import com.soddong.stdy.querydsl.contract.domain.model.Contract;
import com.soddong.stdy.querydsl.contract.port.out.ContractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContractRepositoryImpl implements ContractRepository {

    private final ContractJpaRepository contractJpaRepository;

    public ContractRepositoryImpl(ContractJpaRepository contractJpaRepository) {
        this.contractJpaRepository = contractJpaRepository;
    }

    @Override
    public Contract save(Contract contract) {
        return ContractMapper.toDomain(
                contractJpaRepository.save(ContractMapper.toEntity(contract))
        );
    }

    @Override
    public Optional<Contract> findById(Long id) {
        return contractJpaRepository.findById(id)
                .map(ContractMapper::toDomain);
    }

    @Override
    public List<Contract> findByCustomerId(Long customerId) {
        return contractJpaRepository.findAll().stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .map(ContractMapper::toDomain)
                .toList();
    }

    @Override
    public List<Contract> searchByCondition(ContractQuery query) {
        return contractJpaRepository.searchByCondition(query).stream()
                .map(ContractMapper::toDomain)
                .toList();
    }
}
