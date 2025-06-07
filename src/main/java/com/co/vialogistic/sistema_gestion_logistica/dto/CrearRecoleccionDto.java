package com.co.vialogistic.sistema_gestion_logistica.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CrearRecoleccionDto(
        @NotBlank String nombreRemitente,
        @NotBlank String telefonoRemitente,
        @NotBlank @Email String emailRemitente,
        @NotBlank String nombreDestinatario,
        @NotBlank String telefonoDestinatario,
        @NotNull DireccionDto direccionRemitente,
        @NotNull DireccionDto direccionDestinatario,
        //Asegra que la fecha sea en el futuro
        @NotNull @Future OffsetDateTime fechaHoraProgramadaRecoleccion,
        @NotBlank String descripcionPaquete,
        @NotNull @DecimalMin("0.01") BigDecimal pesoKg,
        @NotNull @DecimalMin("0.01") BigDecimal altoCm,
        @NotNull @DecimalMin("0.01") BigDecimal anchoCm,
        @NotNull @DecimalMin("0.01") BigDecimal largoCm,
        String notasAdministrador
) {
}
