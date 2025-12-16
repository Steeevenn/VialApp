package com.co.vialogistic.sistema_gestion_logistica.service;


import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ModificarDireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaDireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.exception.recolecciones.DireccionNotExistException;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.DireccionesMapper;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.ModificarDireccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import com.co.vialogistic.sistema_gestion_logistica.repository.DireccionesRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ModificarDirecciones {


    private final DireccionesRepository direccionRepository;
    private final ModificarDireccionMapper modificarDireccionMapper;

    public ModificarDirecciones(DireccionesRepository direccionRepository, ModificarDireccionMapper modificarDireccionMapper ) {
        this.direccionRepository = direccionRepository;
        this.modificarDireccionMapper = modificarDireccionMapper;
    }

    @Transactional
    public Direccion responseModificarDirecciones(ModificarDireccionDto modificarDireccionDto, Long idDireccion){


        // 1. Traer la direccion guardada por el id
        Direccion direccion = direccionRepository.findById(idDireccion)
                .orElseThrow(
                        () -> new DireccionNotExistException("La direccion buscada no existe")
                );

        // 2. Usar metodo para mapear entidad Direccion a dto

        modificarDireccionMapper.updateFromDto(modificarDireccionDto, direccion);

        return direccionRepository.save(direccion);
    }

}
