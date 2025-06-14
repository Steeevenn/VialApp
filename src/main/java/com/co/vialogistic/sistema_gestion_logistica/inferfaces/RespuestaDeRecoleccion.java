package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.dto.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;

import java.io.Serializable;
import java.math.BigDecimal;

public interface RespuestaDeRecoleccion extends Serializable {

    RespuestaRecoleccionDto respuestaRecoleccionJson(String nombreRemitente, String telefonoRemitente, String emailRemitente,
                                                     DireccionDto direccionRemitente, String nombreDestinatario, String telefonoDestinatario,
                                                     DireccionDto direccionDestinatario, String descripcionPaquete, BigDecimal pesoKg,
                                                     BigDecimal altoCm, BigDecimal anchoCm, BigDecimal largoCm, String notasAdministrador);

    RespuestaRecoleccionDto respuestaRecoleccionJson(Recoleccion recoleccion, String nombreRemitente, String telefonoRemitente,
                                                     String emailRemitente, DireccionDto direccionRemitente, String nombreDestinatario, String telefonoDestinatario,
                                                     DireccionDto direccionDestinatario, String descripcionPaquete, BigDecimal pesoKg, BigDecimal altoCm,
                                                     BigDecimal anchoCm, BigDecimal largoCm, String notasAdministrador);
}
