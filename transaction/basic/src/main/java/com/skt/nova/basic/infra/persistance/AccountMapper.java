package com.skt.nova.basic.infra.persistance;

import com.skt.nova.basic.domain.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toDomain(AccountJpaEntity entity) {
        return new Account(entity.getId(), entity.getBalance());
    }

    public AccountJpaEntity toEntity(Account domain) {
        return new AccountJpaEntity(domain.getId(), domain.getBalance());
    }
}

