package com.paypilot.common.exception;

public class MerchantNotFoundException extends RuntimeException {
    public MerchantNotFoundException(String message){
        super(message);
    }
}
