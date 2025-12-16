package com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones;

import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoVia;

import java.time.OffsetDateTime;

public record ModificarDireccionDto(

        TipoVia tipoVia,
        String numeroViaPrincipal,
        String numeroPlaca,
        String complemento,
        String barrio,
        String codigoPostal,
        String ciudad,
        String referenciasAdicionales

) {
}
