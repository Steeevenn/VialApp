package com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores;

import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ModificarDireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.DireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DireccionesMapper {

    DireccionDto toDto(Direccion direccion);

    Direccion toEntity(DireccionDto DireccionDto);


}
