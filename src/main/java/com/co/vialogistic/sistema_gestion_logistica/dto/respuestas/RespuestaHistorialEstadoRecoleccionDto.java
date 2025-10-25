    package com.co.vialogistic.sistema_gestion_logistica.dto.respuestas;

    import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;

    import java.time.OffsetDateTime;

    public record RespuestaHistorialEstadoRecoleccionDto(

            Long id,
            Long idRecoleccion,
            EstadoRecoleccion estadoAnterior,
            EstadoRecoleccion estadoNuevo,
            Long cambiadoPorUsuarioId,
            String motivoCambio,
            OffsetDateTime timestampCambio
    ) {
    }
