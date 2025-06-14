package com.co.vialogistic.sistema_gestion_logistica.dto.respuestas;

import com.co.vialogistic.sistema_gestion_logistica.dto.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record RespuestaRecoleccionDto(
        Long administradorAgendoId,
        Usuario DomiciliarioAsinado,
        EstadoRecoleccion estadoRecoleccion,
        OffsetDateTime fechaHoraProgramadaRecoleccion,
        OffsetDateTime fechaCreacion,


        String nombreRemitente,
        String telefonoRemitente,
        String emailRemitente,
        DireccionDto direccioRemitente,
        String nombreDestinatario,
        String telefonoDestinatario,
        DireccionDto direccionDestinatario,
        String descripcionPaquete,
        BigDecimal pesoKg,
        BigDecimal altoCm,
        BigDecimal anchoCm,
        BigDecimal largoCm,
        String notasAdministrador

) {


}
