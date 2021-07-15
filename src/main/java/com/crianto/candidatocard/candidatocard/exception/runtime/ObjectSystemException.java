package com.crianto.candidatocard.candidatocard.exception.runtime;

public class ObjectSystemException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public ObjectSystemException(String msg) {
        super(msg);
    }

    public ObjectSystemException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
