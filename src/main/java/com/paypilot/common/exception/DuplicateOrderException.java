package com.paypilot.common.exception;

public class DuplicateOrderException extends RuntimeException{
    public DuplicateOrderException (String message){
        super(message);
    }
}
