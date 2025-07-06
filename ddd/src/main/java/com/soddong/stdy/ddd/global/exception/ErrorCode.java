package com.soddong.stdy.ddd.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    CONTRACT_ALREADY_EXISTS("C001", "이미 계약이 존재합니다."),
    CUSTOMER_NOT_FOUND("C002", "존재하지 않는 고객입니다."),
    CONTRACT_NOT_FOUND("C003", "존재하지 않는 계약입니다."),
    CONTRACT_CREATION_FAILED("C004", "계약 생성에 실패했습니다."),
    INVALID_CONTRACT_PERIOD("C003", "계약 기간이 유효하지 않습니다."),
    INTERNAL_SERVER_ERROR("G999", "예기치 못한 오류입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}