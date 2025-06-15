package com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores;

import com.co.vialogistic.sistema_gestion_logistica.dto.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.RecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(target ="direccionRemitente" , ignore = true)
    @Mapping(target ="direccionDestinatario" , ignore = true)
    Recoleccion toEntity(CrearRecoleccionDto dto);


    RecoleccionDto toDto (Recoleccion recoleccion);


    default Long map(Usuario value) {
        if(value == null){
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(value.getId());
        return usuario.getId();
    }

}
