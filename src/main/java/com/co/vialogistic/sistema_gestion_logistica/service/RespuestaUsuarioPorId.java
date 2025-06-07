package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.RespuestaUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.RespuestaDeUsuario;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.UsuarioMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

//Clase de construccion de respuesta
@Service
public class RespuestaUsuarioPorId implements RespuestaDeUsuario {

    private final UsuarioMapper usuarioMapper;
    public RespuestaUsuarioPorId(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public RespuestaUsuarioDto construirRespuestaUsuario(Usuario usuario, HttpStatus status, String mensaje, String error) {

        RespuestaUsuarioDto respuesta  = usuarioMapper.usuarioToRespuestaDto(usuario);

        // Crear una nueva instancia del DTO con los campos adicionales
        return new RespuestaUsuarioDto(
                status.value(),
                respuesta.usuario(),
                mensaje,
                respuesta.email(),
                error,
                respuesta.roles()
        );

    }

    @Override
    public RespuestaUsuarioDto construirRespuestaError(String username, String email, HttpStatus status, String mensaje, String error) {
        return null;
    }
}
