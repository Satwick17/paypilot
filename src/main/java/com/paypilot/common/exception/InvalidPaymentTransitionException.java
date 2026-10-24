package com.paypilot.common.exception;

public class InvalidPaymentTransitionException extends RuntimeException {

    public InvalidPaymentTransitionException(String message) {
        super(message);
    }
}