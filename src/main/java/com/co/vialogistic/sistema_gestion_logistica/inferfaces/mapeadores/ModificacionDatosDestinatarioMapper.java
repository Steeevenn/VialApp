package com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores;

import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ModificarDatosDestinatarioRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring" )
public interface ModificacionDatosDestinatarioMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaHoraProgramadaRecoleccion", dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @Mapping(target = "estadoRecoleccion", ignore = true)
    @Mapping(target = "notasDomiciliario", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "archivosAdjuntos", ignore = true)
    @Mapping(target = "historialEstados", ignore = true)
    @Mapping(target ="direccionRemitente" , ignore = true)
    @Mapping(target ="direccionDestinatario" , ignore = true)
    Recoleccion toModificarDatosDestinatarioRecoleccionDto(ModificarDatosDestinatarioRecoleccionDto modificarDatosDestinatarioRecoleccionDto);

   // @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaHoraProgramadaRecoleccion", dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX")
    // @Mapping(target = "estadoRecoleccion", ignore = true)
   // @Mapping(target = "notasDomiciliario", ignore = true)
    //@Mapping(target = "fechaCreacion", ignore = true)
    //@Mapping(target = "fechaActualizacion", ignore = true)
    //@Mapping(target = "archivosAdjuntos", ignore = true)
    //@Mapping(target = "historialEstados", ignore = true)
    // @Mapping(target ="direccionRemitente" , ignore = true)
    //@Mapping(target ="direccionDestinatario" , ignore = true)
    ModificarDatosDestinatarioRecoleccionDto toEntityRecoleccionDto(Recoleccion recoleccion);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "nombreDestinatario", source = "nombreDestinatario")
    @Mapping(target = "telefonoDestinatario", source = "telefonoDestinatario")
    @Mapping(target = "direccionDestinatario", source = "direccionDestinatario")
    @Mapping(target = "descripcionPaquete", source = "descripcionPaquete")
    @Mapping(target = "pesoKg", source = "pesoKg")
    @Mapping(target = "altoCm", source = "altoCm")
    @Mapping(target = "anchoCm", source = "anchoCm")
    @Mapping(target = "largoCm", source = "largoCm")
    @Mapping(target = "notasAdministrador", source = "notasAdministrador")
    void updateFromDto(ModificarDatosDestinatarioRecoleccionDto dto,
                       @MappingTarget Recoleccion recoleccion);

}
