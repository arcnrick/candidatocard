package com.crianto.candidatocard.candidatocard.exception.runtime;

public class ObjectNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
