package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.dto.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;

public interface CreacionDeRecoleccion {
    public Recoleccion crearRecoleccion(CrearRecoleccionDto crearRecoleccion);
}
