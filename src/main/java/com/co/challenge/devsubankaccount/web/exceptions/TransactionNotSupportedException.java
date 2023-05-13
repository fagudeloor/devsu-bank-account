package com.co.challenge.devsubankaccount.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TransactionNotSupportedException extends RuntimeException {

    public TransactionNotSupportedException() {
    }

    public TransactionNotSupportedException(String message) {
        super(message);
    }

    public TransactionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotSupportedException(Throwable cause) {
        super(cause);
    }

    public TransactionNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}