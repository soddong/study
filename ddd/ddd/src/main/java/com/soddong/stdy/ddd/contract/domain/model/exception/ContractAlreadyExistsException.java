package com.soddong.stdy.ddd.contract.domain.model.exception;

public class ContractAlreadyExistsException extends RuntimeException {
    public ContractAlreadyExistsException() {
        super("고객은 이미 계약을 보유하고 있습니다.");
    }
}
