package com.co.vialogistic.sistema_gestion_logistica.dto.creacionales;

import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import jakarta.validation.constraints.NotNull;

import javax.validation.constraints.NotBlank;

public record ArchivosAdjuntosDto(
      @NotBlank Long recoleccionId,
      @NotBlank Long domiciliarioQueSube,
      @NotNull String urlArchivo,
      @NotNull String notasDomiciliario

) {
}
