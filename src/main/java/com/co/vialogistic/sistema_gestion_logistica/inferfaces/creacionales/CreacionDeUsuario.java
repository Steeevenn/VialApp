package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.dto.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;

import java.io.Serializable;

public interface CreacionDeUsuario extends Serializable {

    Usuario crearUsuario(CrearUsuarioDto usuarioDto);
}
