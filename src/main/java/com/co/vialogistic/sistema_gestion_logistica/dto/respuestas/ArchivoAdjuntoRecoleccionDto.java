package com.co.vialogistic.sistema_gestion_logistica.dto.respuestas;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoArchivo;

import java.time.OffsetDateTime;

public record ArchivoAdjuntoRecoleccionDto(
        Long id,
        String nombreOriginalArchivo,
        TipoArchivo tipoArchivo,
        String urlArchivo,
        String contentType,
        Long tamanoBytes,
        OffsetDateTime fechaSubida,
        Long subidoPorUsuario
) {
}
