package com.co.vialogistic.sistema_gestion_logistica.inferfaces;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaCreacionUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


public interface RespuestaDeUsuario extends Serializable {

    RespuestaCreacionUsuarioDto construirRespuestaUsuario(Usuario usuario, HttpStatus status, String mensaje, String error);

    RespuestaCreacionUsuarioDto construirRespuestaError(String username, String email, HttpStatus status, String mensaje, String error);


}
