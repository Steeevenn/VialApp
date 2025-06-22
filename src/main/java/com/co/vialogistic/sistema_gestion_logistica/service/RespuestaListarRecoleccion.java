package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.RespuestaDeRecoleccion;
import org.springframework.stereotype.Service;

@Service
public class RespuestaListarRecoleccion implements RespuestaDeRecoleccion {


    @Override
    public RespuestaListarRecoleccionesDto respuestaRecoleccionJson(RespuestaListarRecoleccionesDto datos) {
        return construirRespuesta(datos);
    }

    private RespuestaListarRecoleccionesDto construirRespuesta(RespuestaListarRecoleccionesDto datos) {

        // Lógica de construcción de la respuesta
        return new RespuestaListarRecoleccionesDto(
                datos.usuarioAgendoId(),
                datos.idRecoleccion(),
                datos.nombreRemitente(),
                datos.telefonoRemitente(),
                datos.emailRemitente(),
                datos.direccionRemitente(),
                datos.nombreDestinatario(),
                datos.telefonoDestinatario(),
                datos.direccionDestinatario(),
                datos.fechaHoraProgramadaRecoleccion(),
                datos.descripcionPaquete(),
                datos.pesoKg(),
                datos.anchoCm(),
                datos.altoCm(),
                datos.largoCm(),
                datos.notasAdministrador()
        );


    }

}












