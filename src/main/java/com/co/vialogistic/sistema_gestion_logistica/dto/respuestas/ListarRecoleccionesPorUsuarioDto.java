package com.co.vialogistic.sistema_gestion_logistica.dto.respuestas;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.DireccionDto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ListarRecoleccionesPorUsuarioDto(
        //Provisional mientras se implementa spring security
        @NotNull Long UsuarioAgendoId,
        @NotBlank String domiciliarioAsignado,
        @NotBlank String nombreRemitente,
        @NotBlank String telefonoRemitente,
        @NotBlank @Email String emailRemitente,
        @NotNull DireccionDto direccionRemitente,
        @NotBlank String nombreDestinatario,
        @NotBlank String telefonoDestinatario,
        @NotNull DireccionDto direccionDestinatario,
        //Asegurara que la fecha que se cargue sea en el futuro
        @NotNull @Future OffsetDateTime fechaHoraProgramadaRecoleccion,
        @NotBlank String descripcionPaquete,
        @NotNull @DecimalMin("0.01") BigDecimal pesoKg,
        @NotNull @DecimalMin("0.01") BigDecimal altoCm,
        @NotNull @DecimalMin("0.01") BigDecimal anchoCm,
        @NotNull @DecimalMin("0.01") BigDecimal largoCm,
        String notasAdministrador,
        String notasDomiciliario

) {
    public ListarRecoleccionesPorUsuarioDto conDomiciliarioAsginado (String domiciliarioAsginado){
        return new ListarRecoleccionesPorUsuarioDto
                (UsuarioAgendoId,
                        domiciliarioAsginado,
                nombreRemitente,
                telefonoRemitente,
                emailRemitente,
                direccionRemitente,
                nombreDestinatario,
                telefonoDestinatario,
                direccionDestinatario,
                fechaHoraProgramadaRecoleccion,
                descripcionPaquete,
                pesoKg,
                altoCm,
                anchoCm,
                largoCm,
                notasAdministrador,
                notasDomiciliario
                );
    }
}
