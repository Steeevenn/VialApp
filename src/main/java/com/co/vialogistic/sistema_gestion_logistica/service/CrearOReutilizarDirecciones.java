package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.DireccionesMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import com.co.vialogistic.sistema_gestion_logistica.repository.DireccionesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CrearOReutilizarDirecciones {

private final DireccionesRepository direccionesRepository;
private final DireccionesMapper direccionesMapper;

    public CrearOReutilizarDirecciones(DireccionesRepository direccionesRepository, DireccionesMapper direccionesMapper) {
        this.direccionesRepository = direccionesRepository;
        this.direccionesMapper = direccionesMapper;

    }
    public Direccion crearOReutilizarDirecciones(DireccionDto direccionDto) {

        Optional <Direccion> direccionExistente = direccionesRepository.findByCamposUnicos(
                direccionDto.tipoVia(),
                direccionDto.numeroViaPrincipal(),
                direccionDto.numeroPlaca(),
                direccionDto.complemento(),
                direccionDto.barrio(),
                direccionDto.ciudad()
                );
        return direccionExistente.orElseGet(() ->{
            Direccion nuevaDireccion = direccionesMapper.toEntity(direccionDto);
                    return direccionesRepository.save(nuevaDireccion);
        });

    }
}
