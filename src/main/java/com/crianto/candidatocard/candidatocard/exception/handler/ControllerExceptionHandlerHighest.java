package com.crianto.candidatocard.candidatocard.exception.handler;

import com.crianto.candidatocard.candidatocard.exception.CustomError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandlerHighest {

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<List<CustomError>> handleEmptyResultDataAccessException(
            EmptyResultDataAccessException ex, WebRequest request) {

        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.toString();

        List<CustomError> erros = Collections.singletonList(
                new CustomError(HttpStatus.CONFLICT.value(), msgUsuario, msgDesenvolvedor));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erros);
    }
}