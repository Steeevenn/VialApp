package com.co.vialogistic.sistema_gestion_logistica.exception;

import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.InvalidateEmailException;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.RolNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotAdminException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorDeExcepciones {




    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<?> handleUsuarioNotFound (UsuarioNotFoundException ex){

        return buildErrorResponse(
         HttpStatus.NOT_FOUND,
                "USUARIO NO ENCONTRADO",
                ex.getMessage()
            );
    }


    @ExceptionHandler(InvalidateEmailException.class)
    public ResponseEntity<?> handleEmailUsuario(InvalidateEmailException invalidateEmail){

        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "EMAIL INVALIDO",
                invalidateEmail.getMessage()
        );

    }

    @ExceptionHandler(RolNotFoundException.class)
    public ResponseEntity<?> rolNotFoundEx(RolNotFoundException ex){
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "ROL NO VALIDO",
                ex.getMessage()
        );
    }


    @ExceptionHandler(UsuarioNotAdminException.class)
    public ResponseEntity<?> userNotAmdminEx(UsuarioNotAdminException ex){
        return buildErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Usuario no es ADMINISTRADOR",
                ex.getMessage()
        );
    }



    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status,
            String errorCode,
            String message
    ){

        Map<String, Object> body  = new HashMap<>();
        body.put("timestamp", OffsetDateTime.now());
        body.put("status", status.value());
        body.put("Error Code", errorCode);
        body.put("Message", message);

        return ResponseEntity.status(status).body(body);
    }

}
