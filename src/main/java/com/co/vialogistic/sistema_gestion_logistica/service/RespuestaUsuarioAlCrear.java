package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaCreacionUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.RespuestaDeUsuario;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.UsuarioMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RespuestaUsuarioAlCrear implements RespuestaDeUsuario {

    private final UsuarioMapper usuarioMapper;

    public RespuestaUsuarioAlCrear(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public RespuestaCreacionUsuarioDto construirRespuestaUsuario(Usuario usuario, HttpStatus status, String mensaje, String error) {

        RespuestaCreacionUsuarioDto respuestaBase  = usuarioMapper.usuarioToRespuestaDto(usuario);

        // Crear una nueva instancia del DTO con los campos adicionales
        return new RespuestaCreacionUsuarioDto(
                status.value(),
                respuestaBase.usuario(),
                mensaje,
                respuestaBase.email(),
                error,
                respuestaBase.roles()
        );

    }

    @Override
    public RespuestaCreacionUsuarioDto construirRespuestaError(String username, String email, HttpStatus status, String mensaje, String error) {
        return new RespuestaCreacionUsuarioDto(
                status.value(),
                username,
                mensaje,
                email,
                error,
                null // roles null en caso de error
        );
    }

    }
