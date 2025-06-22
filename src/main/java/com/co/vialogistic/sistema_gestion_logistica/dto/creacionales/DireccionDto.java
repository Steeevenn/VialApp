package com.co.vialogistic.sistema_gestion_logistica.dto.creacionales;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoVia;
import jakarta.validation.constraints.*;

public record DireccionDto(

        @NotNull TipoVia tipoVia,
        @NotBlank String numeroViaPrincipal,
        @NotBlank String numeroPlaca,
        @NotBlank String complemento,
        @NotBlank String barrio,
        String codigoPostal,
        @NotBlank String ciudad,
        String referenciasAdicionales

) {

    public static DireccionDto normalizarDireccionDto(DireccionDto dto) {
        return new DireccionDto(
                dto.tipoVia(),
                dto.numeroViaPrincipal().trim(),
                dto.numeroPlaca().trim(),
                dto.complemento().trim(),
                dto.barrio().trim().toLowerCase(),
                dto.codigoPostal() != null ? dto.codigoPostal().trim() : null,
                dto.ciudad().trim().toLowerCase(),
                dto.referenciasAdicionales() != null ? dto.referenciasAdicionales().trim() : null
        );
    }

}
