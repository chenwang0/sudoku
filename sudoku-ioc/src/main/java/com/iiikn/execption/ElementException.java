package com.iiikn.execption;

public class ElementException extends IOCException {

    public ElementException(String msg) {
        super(msg);
    }

    public ElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
