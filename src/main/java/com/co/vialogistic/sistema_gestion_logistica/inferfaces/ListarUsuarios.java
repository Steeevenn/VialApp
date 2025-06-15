package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;

import java.io.Serializable;
import java.util.List;

public interface ListarUsuarios  extends Serializable {

    public List<Usuario> getUsuarios();

}
