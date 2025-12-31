package com.co.vialogistic.sistema_gestion_logistica.dto.creacionales;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import org.apache.logging.log4j.message.Message;

import javax.validation.constraints.NotNull;

public record CrearHistorialEstadoDto(

        @NotNull (message = "ID de recoleccion obligatorio")
        Long recoleccionId,

        @NotNull(message = "Estado nuevo obligatorio")
        EstadoRecoleccion estadoNuevo,

        Long cambiadoPorUsuarioId,

        String motivoCambio

) {
}
