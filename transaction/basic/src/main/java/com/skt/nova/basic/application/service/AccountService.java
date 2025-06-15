// com.skt.nova.basic.application.service.AccountService
package com.skt.nova.basic.application.service;

import com.skt.nova.basic.domain.model.Account;
import com.skt.nova.basic.domain.port.in.AccountCreateUseCase;
import com.skt.nova.basic.domain.port.in.AccountReadUseCase;
import com.skt.nova.basic.domain.port.out.LoadAccountPort;
import com.skt.nova.basic.domain.port.out.SaveAccountPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountReadUseCase, AccountCreateUseCase {

    private final LoadAccountPort loadPort;
    private final SaveAccountPort savePort;

    @Lazy
    @Autowired
    private AccountService self;

    @Override
    @Transactional
    public void read(Long accountId) {
        Account account1 = loadPort.findById(accountId).orElseThrow();
        System.out.println("[READ RESULT] " + account1.getBalance());
    }

    @Override
    @Transactional
    public void update(Long accountId, int newBalance) {
        Account account = loadPort.findById(accountId).orElseThrow();
        Account updated = account.withBalance(newBalance);
        savePort.save(updated);
        System.out.println("[UPDATE RESULT] " + updated.getBalance());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void create(int newBalance) {
        System.out.println("[계좌 생성] 시작");
        savePort.save(Account.builder().balance(newBalance).build());
        System.out.println("[계좌 생성] 완료");
    }

    @Transactional
    public void createAndUpdateThenFail() {
        self.create(10000);
        update(1L, 999);

        // 예외 발생 (현재 트랜잭션 롤백)
        throw new RuntimeException("예외 발생으로 롤백 시도");
    }
}
