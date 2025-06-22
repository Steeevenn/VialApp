package com.co.vialogistic.sistema_gestion_logistica.dto.respuestas;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Rol;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public record RespuestaListarUsuariosDto(

        @JsonProperty("id")
        Long id,

        @JsonProperty("username")
        String username,

        @JsonProperty("email")
        String email,

        @JsonProperty("nombre_completo")
        String nombreCompleto,

        @JsonProperty("telefono")
        String telefono,

        @JsonProperty("roles")
        Set<String> roles


        ) {
}
