package com.skt.nova.basic.domain.port.out;

import com.skt.nova.basic.domain.model.Account;

public interface SaveAccountPort {
    void save(Account account);
}
