package com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores;

import com.co.vialogistic.sistema_gestion_logistica.dto.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DireccionesMapper {

    DireccionDto toDto(Direccion direccion);

    Direccion toEntity(DireccionDto direccionDto);

}
