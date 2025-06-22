package com.co.vialogistic.sistema_gestion_logistica.exception;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(Usuario usuario) {
        System.out.println("Usuario " + usuario.getUsername() + "no encontrado" );

    }

}

