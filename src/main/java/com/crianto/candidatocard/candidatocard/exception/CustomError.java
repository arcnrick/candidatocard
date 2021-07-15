package com.crianto.candidatocard.candidatocard.exception;

import java.io.Serializable;

public class CustomError implements Serializable {
    private static final Long serialVersionUID = 1L;
    private final String mensagemUsuario;
    private final String mensagemDesenvolvedor;
    private final Integer status;

    public CustomError(Integer status, String mensagemUsuario, String mensagemDesenvolvedor) {
        this.status = status;
        this.mensagemUsuario = mensagemUsuario;
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }

    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }

}
