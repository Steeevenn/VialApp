package com.co.vialogistic.sistema_gestion_logistica.dto.respuestas;


import java.util.*;

public record RespuestaCreacionUsuarioDto(
        int status,
        String usuario,
        String mensaje,
        String email,
        String error,
        Set<String>roles
){


}
