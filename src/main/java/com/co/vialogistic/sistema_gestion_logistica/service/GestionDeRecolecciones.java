package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.AgendadorDeDomiciliarioException;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.RecoleccionNotExistException;
import com.co.vialogistic.sistema_gestion_logistica.exception.usuario.UsuarioNotAdminException;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.validadores.UsuarioValidador;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionDeRecolecciones {

    private final UsuarioRepository usuarioRepository;
    private final RecoleccionRepository recoleccionRepository;
    private final RecoleccionMapper recoleccionMapper;
    private final UsuarioValidador usuarioValidador;

    public GestionDeRecolecciones(UsuarioRepository usuarioRepository, RecoleccionRepository recoleccionRepository, RecoleccionMapper recoleccionMapper, UsuarioValidador usuarioValidador) {
        this.usuarioRepository = usuarioRepository;
        this.recoleccionRepository = recoleccionRepository;
        this.recoleccionMapper = recoleccionMapper;
        this.usuarioValidador = usuarioValidador;
    }
    @Transactional
    public void AsignarRecoleccionAUsuario(Long idUsuarioQueAsigno, Long idDomiciliario, Long idRecoleccion) {

        // 1. Validar existencia del domiciliario

     Usuario domiciliario =  usuarioValidador.validarRolDomiciliario(idDomiciliario);


        List<Recoleccion> recoleccionFiltrada = recoleccionRepository.listarRecoleccionesPorUsuario(idUsuarioQueAsigno,EstadoRecoleccion.PENDIENTE_ASIGNACION)
                .stream()
                .filter(r -> r.getId().equals(idRecoleccion))
                .toList();

        // 2. Verificar si se encontró la recolección
        if (recoleccionFiltrada.isEmpty()) {
            throw new AgendadorDeDomiciliarioException(
                    "Recolección no encontrada o no está pendiente de asignación para este usuario o esta recoleccion no pertece a este usuario " + idUsuarioQueAsigno
            );
        }

        // 3. Asignar y actualizar
        recoleccionFiltrada.forEach(m -> {
                            m.setDomiciliarioAsginado(domiciliario);
                            m.setEstadoRecoleccion(EstadoRecoleccion.ASIGNADA);
                        });
         recoleccionRepository.saveAll(recoleccionFiltrada);
    }

    // Servicio para listar recolecciones de un usuario especifico

    public List<RespuestaListarRecoleccionesDto> ListaRecolecciones(Long idUsuarioQueAsigno, EstadoRecoleccion estadoRecoleccion ){

        return recoleccionRepository.listarRecoleccionesPorUsuario(idUsuarioQueAsigno,estadoRecoleccion).stream()
                .map(recoleccionMapper::toDto)
                .collect(Collectors.toList());

    }

    //Servicio para listar el total de recolecciones
    public List<RespuestaListarRecoleccionesDto> listarRecoleccionesAll(){

        List<Recoleccion> listaRecoleccionesTotal = recoleccionRepository.findAll();
        if(listaRecoleccionesTotal.isEmpty()){
            throw new RecoleccionNotExistException("No hay recolecciones asignadas");
        }

        return listaRecoleccionesTotal.stream()
                .map(recoleccionMapper::toDto)
                .toList();

    }



    }




