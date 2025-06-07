package com.co.vialogistic.sistema_gestion_logistica.inferfaces;
import com.co.vialogistic.sistema_gestion_logistica.model.dto.RespuestaUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


public interface RespuestaDeUsuario extends Serializable {

    RespuestaUsuarioDto construirRespuestaUsuario(Usuario usuario, HttpStatus status, String mensaje, String error);

    RespuestaUsuarioDto construirRespuestaError(String username, String email, HttpStatus status, String mensaje, String error);


}
