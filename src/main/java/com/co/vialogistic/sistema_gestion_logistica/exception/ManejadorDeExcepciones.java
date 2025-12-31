package com.co.vialogistic.sistema_gestion_logistica.exception;

import com.co.vialogistic.sistema_gestion_logistica.exception.archivosAdjuntos.ArchivoAdjuntoNotSaveException;
import com.co.vialogistic.sistema_gestion_logistica.exception.archivosAdjuntos.TamanoNoValidoParaArchivoException;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.DireccionNotExistException;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.RecoleccionNotExistException;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.historialEstadosRecoleccion.TransicionNoPermitidaException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.InvalidateEmailException;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.RolNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotAdminException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

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

    @ExceptionHandler(RecoleccionNotExistException.class)
    public ResponseEntity<?> recoleccionNotExistEx(RecoleccionNotExistException ex){
         return buildErrorResponse(
                 HttpStatus.NO_CONTENT,
                 "NO EXISTEN RECOLECCIONES",
                 ex.getMessage()
         );
    }

    @ExceptionHandler(DireccionNotExistException.class)
    public ResponseEntity<?> direccionNotExistEx(DireccionNotExistException ex) {

        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "DIRECCION NO EXISTE",
                ex.getMessage()
        );
    }



    @ExceptionHandler(ArchivoAdjuntoNotSaveException.class)
    public  ResponseEntity<?> archivoAdjuntoNotFound(ArchivoAdjuntoNotSaveException ex){
               return buildErrorResponse(
                       HttpStatus.NOT_FOUND,
                       "ARCHIVO NO VALIDO",
                       ex.getMessage()
               );
    }

    @ExceptionHandler(TamanoNoValidoParaArchivoException.class)
    public ResponseEntity<?> tamañoEnBytesNoValidoParaArchivoAdjunto(TamanoNoValidoParaArchivoException ex){
        return buildErrorResponse(
                HttpStatus.PAYLOAD_TOO_LARGE,
                "TAMAÑO INVALIDO",
                ex.getMessage()
        );

    }
   @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> multipartExceptionHandler(MultipartException ex){

        return buildErrorResponse(
                HttpStatus.PAYLOAD_TOO_LARGE,
                "ARCHIVO ENVIADO DEMASIADO GRANDE MAX 15 MB",
                ex.getMessage()

        );

   }

   @ExceptionHandler(TransicionNoPermitidaException.class)
   public ResponseEntity<?> transicionNoValida(TransicionNoPermitidaException ex){
        return buildErrorResponse(
                HttpStatus.CONFLICT,
                "CAMBIO DE ESTADO INVALIDO",
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
