package com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;

public interface CreacionDeRecoleccion {
    public Recoleccion crearRecoleccion(CrearRecoleccionDto crearRecoleccion);
}
