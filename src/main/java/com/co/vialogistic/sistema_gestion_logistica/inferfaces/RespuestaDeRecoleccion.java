package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.dto.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.RecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaRecoleccionDto;

import java.io.Serializable;
import java.math.BigDecimal;

public interface RespuestaDeRecoleccion extends Serializable {


    RecoleccionDto respuestaRecoleccionJson(RecoleccionDto datos);

}
