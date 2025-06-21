package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.controller.Recoleccciones;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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

    //Obtener domiciliario como entidad
        Usuario domiciliario = usuarioRepository.findById(idDomiciliario)
                .orElseThrow(() -> new EntityNotFoundException("Domiciliario no encontrado"));

        //Traer la lista de recoelcciones que un usuario en especifico agendo
        List <Recoleccion> recolecciones = recoleccionRepository.listarRecoleccionesPorUsuario(
                idUsuarioQueAsigno,
                EstadoRecoleccion.PENDIENTE_ASIGNACION
                );


        //Iterar sobre las recolecciones que el usuario x agendo y modificar el domicialiaio asginado
                recolecciones.forEach(
                        n ->{
                            if(n.getId().equals(idRecoleccion)){
                            n.setDomiciliarioAsginado(domiciliario);
                            n.setEstadoRecoleccion(EstadoRecoleccion.ASIGNADA);}
                        });


        System.out.println("Domiciliaroo asginado con el id : " + domiciliario.getId());
        //Traer el estado de la recoleccion
                    recolecciones.forEach(n -> n.setEstadoRecoleccion(EstadoRecoleccion.ASIGNADA));
         recoleccionRepository.saveAll(recolecciones);

    }


    public List<RespuestaListarRecoleccionesDto> ListaRecolecciones(Long idUsuarioQueAsigno, EstadoRecoleccion estadoRecoleccion){


        return recoleccionRepository.listarRecoleccionesPorUsuario(idUsuarioQueAsigno,EstadoRecoleccion.PENDIENTE_ASIGNACION).stream()
                .map(recoleccionMapper::toDto)
                .collect(Collectors.toList());


    }




    }




