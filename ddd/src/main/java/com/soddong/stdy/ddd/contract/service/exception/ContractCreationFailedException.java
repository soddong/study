package com.soddong.stdy.ddd.contract.service.exception;

public class ContractCreationFailedException extends RuntimeException {
    public ContractCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}