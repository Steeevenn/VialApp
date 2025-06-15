package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;

import java.io.Serializable;

public interface RespuestaDeRecoleccion extends Serializable {


    RespuestaListarRecoleccionesDto respuestaRecoleccionJson(RespuestaListarRecoleccionesDto datos);

}
