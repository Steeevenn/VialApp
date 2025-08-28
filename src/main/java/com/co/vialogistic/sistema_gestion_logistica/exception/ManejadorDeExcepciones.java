package com.co.vialogistic.sistema_gestion_logistica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManejadorDeExcepciones {

    @ExceptionHandler(AgendadorDeDomiciliarioException.class)
    public ResponseEntity<String> manejadorAsinacionesUsuarios(AgendadorDeDomiciliarioException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    public ResponseEntity<String> rolNotFoundEx(RolNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
