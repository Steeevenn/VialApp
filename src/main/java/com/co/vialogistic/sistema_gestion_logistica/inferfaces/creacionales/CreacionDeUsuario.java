package com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;

import java.io.Serializable;

public interface CreacionDeUsuario extends Serializable {

    Usuario crearUsuario(CrearUsuarioDto usuarioDto);
}
