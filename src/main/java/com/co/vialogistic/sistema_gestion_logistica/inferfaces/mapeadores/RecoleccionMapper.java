package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.dto.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" )
public interface RecoleccionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "domiciliarioAsginado", ignore = true) // Campo en la entidad
    @Mapping(target = "fechaHoraProgramadaRecoleccion", dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @Mapping(target = "estadoRecoleccion", ignore = true)
    @Mapping(target = "notasDomiciliario", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "archivosAdjuntos", ignore = true)
    @Mapping(target = "historialEstados", ignore = true)

    //estos datos de direccion los maneja el servico de creacion de recoleccion
    @Mapping(target ="direccionRecoleccion" , ignore = true)
    @Mapping(target ="direccionEntrega" , ignore = true)
    Recoleccion toEntity(CrearRecoleccionDto dto);
}
