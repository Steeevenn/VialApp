package com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores;

import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ModificarDireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaDireccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Direccion;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ModificarDireccionMapper {

    RespuestaDireccionDto toDto (Direccion direccion);

    Direccion toEntity (ModificarDireccionDto direccionDto);


    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ModificarDireccionDto modificarDireccionDto, @MappingTarget Direccion direccion);
}
