package com.iiikn.execption;

public class FoundElementException extends RuntimeException {

    public FoundElementException(String msg) {
        super(msg);
    }

    public FoundElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
