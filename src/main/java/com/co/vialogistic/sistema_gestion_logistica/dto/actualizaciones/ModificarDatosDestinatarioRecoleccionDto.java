package com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ModificarDatosDestinatarioRecoleccionDto(
        //Provisional mientras se implementa spring security
        @NotBlank Long UsuarioAgendoId,
        @NotBlank Long domiciliarioAsignado,
        @NotBlank String nombreDestinatario,
        @NotBlank String telefonoDestinatario,
        @NotNull Direccion direccionDestinatario,
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
