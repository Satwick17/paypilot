package com.paypilot.common.exception;

public class DuplicatePaymentReferenceException extends RuntimeException {
    public DuplicatePaymentReferenceException(String message) {
        super(message);
    }
}
