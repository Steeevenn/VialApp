package com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;

import javax.validation.constraints.NotNull;

public record ModificarEstadoRecoleccionDto(


        @NotNull(message = "ID de recoleccion obligatorio")
        Long recoleccionId,

        EstadoRecoleccion estadoAnterior,

        @NotNull(message = "Estado nuevo obligatorio")
        EstadoRecoleccion estadoNuevo,

        Long cambiadoPorUsuarioId,

        String motivoCambio
) {

}
