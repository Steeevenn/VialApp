package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.controller.Recoleccciones;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;
import com.co.vialogistic.sistema_gestion_logistica.exception.AgendadorDeDomiciliarioException;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestionDeRecolecciones {

    private final UsuarioRepository usuarioRepository;
    private final RecoleccionRepository recoleccionRepository;
    private final RecoleccionMapper recoleccionMapper;

    public GestionDeRecolecciones(UsuarioRepository usuarioRepository, RecoleccionRepository recoleccionRepository, Recoleccciones recoleccciones, RecoleccionMapper recoleccionMapper) {
        this.usuarioRepository = usuarioRepository;
        this.recoleccionRepository = recoleccionRepository;
        this.recoleccionMapper = recoleccionMapper;
    }
    @Transactional
    public void AsignarRecoleccionAUsuario(Long idUsuarioQueAsigno, Long idDomiciliario, Long idRecoleccion) {

        // 1. Validar existencia del domiciliario
        Usuario domiciliario = usuarioRepository.findById(idDomiciliario)
                .stream().filter(r -> r.getRoles().stream()
                        .anyMatch(rol -> rol.getNombre().equals(RolNombre.DOMICILIARIO)))
                .findFirst()
                .orElseThrow(()->new  AgendadorDeDomiciliarioException(idDomiciliario));


        List<Recoleccion> recoleccionFiltrada = recoleccionRepository.listarRecoleccionesPorUsuario(idUsuarioQueAsigno,EstadoRecoleccion.PENDIENTE_ASIGNACION)
                .stream()
                .filter(r -> r.getId().equals(idRecoleccion))
                .toList();

        // 2. Verificar si se encontró la recolección
        if (recoleccionFiltrada.isEmpty()) {
            throw new AgendadorDeDomiciliarioException(
                    "Recolección no encontrada o no está pendiente de asignación para este usuario"
            );
        }

        // 3. Asignar y actualizar
        recoleccionFiltrada.forEach(m -> {
                            m.setDomiciliarioAsginado(domiciliario);
                            m.setEstadoRecoleccion(EstadoRecoleccion.ASIGNADA);
                        });
         recoleccionRepository.saveAll(recoleccionFiltrada);
    }


    public List<RespuestaListarRecoleccionesDto> ListaRecolecciones(Long idUsuarioQueAsigno, EstadoRecoleccion estadoRecoleccion){

        return recoleccionRepository.listarRecoleccionesPorUsuario(idUsuarioQueAsigno,EstadoRecoleccion.PENDIENTE_ASIGNACION).stream()
                .map(recoleccionMapper::toDto)
                .collect(Collectors.toList());

    }




    }




