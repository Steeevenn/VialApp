package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.RecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.RespuestaDeRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RespuestaRecoleccionJson implements RespuestaDeRecoleccion {


    @Override
    public RecoleccionDto respuestaRecoleccionJson(RecoleccionDto datos) {
        return construirRespuesta(datos);
    }

    private RecoleccionDto construirRespuesta(RecoleccionDto datos) {
        // Lógica de construcción de la respuesta



        return new RecoleccionDto(
                datos.usuarioAgendoId(),
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












