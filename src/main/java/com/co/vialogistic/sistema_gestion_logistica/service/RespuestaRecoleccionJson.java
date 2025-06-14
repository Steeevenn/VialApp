package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.RespuestaDeRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class RespuestaRecoleccionJson implements RespuestaDeRecoleccion {

    private final RecoleccionMapper recoleccionMapper;

    public RespuestaRecoleccionJson(RecoleccionMapper recoleccionMapper, UsuarioRepository usuarioRepository) {
        this.recoleccionMapper = recoleccionMapper;
    }





    @Override
    public RespuestaRecoleccionDto respuestaRecoleccionJson(String nombreRemitente, String telefonoRemitente,
                                                            String emailRemitente, DireccionDto direccionRemitente, String nombreDestinatario, String telefonoDestinatario,
                                                            DireccionDto direccionDestinatario, String descripcionPaquete,
                                                            BigDecimal pesoKg, BigDecimal altoCm, BigDecimal anchoCm, BigDecimal largoCm, String notasAdministrador) {
return null;
    }

    @Override
    public RespuestaRecoleccionDto respuestaRecoleccionJson(Recoleccion recoleccion, String nombreRemitente, String telefonoRemitente, String emailRemitente,
                                                            DireccionDto direccionRemitente, String nombreDestinatario, String telefonoDestinatario,
                                                            DireccionDto direccionDestinatario, String descripcionPaquete, BigDecimal pesoKg, BigDecimal altoCm,
                                                            BigDecimal anchoCm, BigDecimal largoCm, String notasAdministrador) {

        Usuario usuarioAgendo = recoleccion.getAdministradorAgendo();
        Long id = usuarioAgendo.getId();

        RespuestaRecoleccionDto recolecciones = recoleccionMapper.toDto(recoleccion);

        return new RespuestaRecoleccionDto(
                id,
                recolecciones.DomiciliarioAsinado(),
                recolecciones.estadoRecoleccion(),
                recolecciones.fechaHoraProgramadaRecoleccion(),
                recolecciones.fechaCreacion(),

                nombreRemitente,
                telefonoRemitente,
                emailRemitente,
                direccionRemitente,
                nombreDestinatario,
                telefonoDestinatario,
                direccionDestinatario,
                descripcionPaquete,
                pesoKg,
                altoCm,
                anchoCm,
                largoCm,
                notasAdministrador

        );


    }

}
