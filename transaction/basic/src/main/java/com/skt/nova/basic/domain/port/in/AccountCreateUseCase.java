package com.skt.nova.basic.domain.port.in;

public interface AccountCreateUseCase {
    void update(Long accountId, int newBalance);
    void create(int newBalance);
    void createAndUpdateThenFail();
}

