package com.soddong.stdy.ddd.contract.domain.model.exception;

public class ContractNotFoundException extends RuntimeException {
    public ContractNotFoundException(Long contractId) {
        super("계약이 존재하지 않습니다. ID: " + contractId);
    }
}