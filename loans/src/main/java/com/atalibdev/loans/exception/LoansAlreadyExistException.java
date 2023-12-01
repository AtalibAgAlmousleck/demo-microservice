package com.atalibdev.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LoansAlreadyExistException extends RuntimeException {
    public LoansAlreadyExistException(String message) {
        super(message);
    }
}
