package com.co.vialogistic.sistema_gestion_logistica.dto;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CrearRecoleccionDto(

        //Provisional mientras se implementa spring security
        @NotNull Long UsuarioAgendoId,
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
        String notasAdministrador
) {
}
