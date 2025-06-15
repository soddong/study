package com.skt.nova.basic.infra.persistance;

import com.skt.nova.basic.domain.model.Account;
import com.skt.nova.basic.domain.port.out.LoadAccountPort;
import com.skt.nova.basic.domain.port.out.SaveAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, SaveAccountPort {

    private final AccountJpaRepository jpaRepository;
    private final AccountMapper mapper;

    @Override
    public Optional<Account> findById(Long accountId) {
        return jpaRepository.findById(accountId)
                .map(mapper::toDomain);
    }

    @Override
    public void save(Account account) {
        jpaRepository.save(mapper.toEntity(account));
    }
}
