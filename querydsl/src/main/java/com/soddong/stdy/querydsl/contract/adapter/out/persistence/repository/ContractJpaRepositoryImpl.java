package com.soddong.stdy.querydsl.contract.adapter.out.persistence.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soddong.stdy.querydsl.contract.adapter.in.web.dto.ContractQuery;
import com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity.ContractJpaEntity;
import com.soddong.stdy.querydsl.contract.adapter.out.persistence.entity.QContractJpaEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ContractJpaRepositoryImpl implements ContractJpaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ContractJpaRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ContractJpaEntity> searchByCondition(ContractQuery query) {
        QContractJpaEntity c = QContractJpaEntity.contractJpaEntity;
        BooleanBuilder builder = new BooleanBuilder();

        if (query.status() != null) {
            builder.and(c.status.eq(query.status()));
        }

        LocalDate from = query.startDateFrom();
        LocalDate to = query.startDateTo();

        if (from != null && to != null) {
            builder.and(c.contractPeriod.startDate.between(from, to));
        } else if (from != null) {
            builder.and(c.contractPeriod.startDate.goe(from));
        } else if (to != null) {
            builder.and(c.contractPeriod.startDate.loe(to));
        }

        return queryFactory.selectFrom(c)
                .where(builder)
                .fetch();
    }

}
