package com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;

public  class RolNotFoundException extends RuntimeException {
    public RolNotFoundException(RolNombre rolNombre) {

        super("Rol no encontrado: " + rolNombre.name());
    }
}
