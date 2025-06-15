package com.skt.nova.basic.domain.port.out;

import com.skt.nova.basic.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface LoadAccountPort {
    Optional<Account> findById(Long accountId);
}

