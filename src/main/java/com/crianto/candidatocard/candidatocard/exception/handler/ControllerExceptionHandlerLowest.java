package com.crianto.candidatocard.candidatocard.exception.handler;

import com.crianto.candidatocard.candidatocard.exception.CustomError;
import com.crianto.candidatocard.candidatocard.exception.runtime.ObjectNotFoundException;
import com.crianto.candidatocard.candidatocard.exception.runtime.ObjectSystemException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandlerLowest {

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<List<CustomError>> objectNotFoundException(
            ObjectNotFoundException ex, WebRequest request) {

        String mensagemUsuario = ex.getMessage();
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        List<CustomError> erros = Collections.singletonList(new CustomError(HttpStatus.NOT_FOUND.value(),
                mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
    }

    @ExceptionHandler({ObjectSystemException.class})
    public ResponseEntity<List<CustomError>> objectSystemException(
            ObjectSystemException ex, WebRequest request) {

        String mensagemUsuario = ex.getMessage();
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);

        List<CustomError> erros = Collections.singletonList(new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erros);
    }
}