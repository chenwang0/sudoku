package com.iiikn.execption;

public class IOCException extends RuntimeException {

    public IOCException(String msg) {
        super(msg);
    }

    public IOCException(String message, Throwable cause) {
        super(message, cause);
    }
}
