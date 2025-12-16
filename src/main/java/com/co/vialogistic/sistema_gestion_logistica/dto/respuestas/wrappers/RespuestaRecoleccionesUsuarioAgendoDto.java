package com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.wrappers;

import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;

import java.util.List;

public record RespuestaRecoleccionesUsuarioAgendoDto(

        Long idUsuarioAgendador,
        String estado,
        List<RespuestaListarRecoleccionesDto> recolecciones
) {
}
