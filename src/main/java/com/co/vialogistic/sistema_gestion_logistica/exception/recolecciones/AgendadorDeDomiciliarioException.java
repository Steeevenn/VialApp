package com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones;

public class AgendadorDeDomiciliarioException extends RuntimeException {

    public AgendadorDeDomiciliarioException(Long usuarioId) {

        super("Id de usario " + usuarioId + " no es un DOMICILIARIO por favor ingrese un domiciliario valido");

    }

    public AgendadorDeDomiciliarioException(String mensaje) {
        super(mensaje);
    }
}
