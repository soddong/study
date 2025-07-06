package com.soddong.stdy.ddd.global.exception;

import com.soddong.stdy.ddd.contract.domain.model.exception.ContractAlreadyExistsException;
import com.soddong.stdy.ddd.contract.domain.model.exception.ContractNotFoundException;
import com.soddong.stdy.ddd.customer.domain.model.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soddong.stdy.ddd.contract.service.exception.*;
import org.springframework.http.ResponseEntity;

import static com.soddong.stdy.ddd.global.exception.ErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(CUSTOMER_NOT_FOUND));
    }

    @ExceptionHandler(ContractNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleContractNotFound(ContractNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(CONTRACT_NOT_FOUND));
    }

    @ExceptionHandler(ContractAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(ContractAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(CONTRACT_ALREADY_EXISTS));
    }

    @ExceptionHandler(ContractCreationFailedException.class)
    public ResponseEntity<ErrorResponse> handleCreationFailed(ContractCreationFailedException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(CONTRACT_CREATION_FAILED));
    }

    // 알 수 없는 모든 예외 처리 (Fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknownException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(INTERNAL_SERVER_ERROR));
    }
}