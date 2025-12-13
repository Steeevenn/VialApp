package com.co.vialogistic.sistema_gestion_logistica.exception.usuario;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(String message) {
        super( message);
    }

    //Aplico concepto de sobrecarga de constructores
    public UsuarioNotFoundException(Long id) {
        super("Usuario no encontrado con id=" + id);
    }

}

