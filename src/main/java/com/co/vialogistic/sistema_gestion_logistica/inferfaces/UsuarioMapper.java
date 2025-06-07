package com.co.vialogistic.sistema_gestion_logistica.inferfaces;

import com.co.vialogistic.sistema_gestion_logistica.model.dto.*;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.*;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import org.mapstruct.*;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "activo", constant = "true")
    //Mapo de Creacion de usuario
    Usuario toEntity(CrearUsuarioDto crearUsuario);

    // Si hay campos null, no se modifican se ignoran y quedan losd datos existentes
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //Metodo de actualizacion de usuariodto a usuario entity
    CrearUsuarioDto updateFromDto (ActualizarUsuarioDto actualizarUsuarioDto, Usuario usuario);


    //Mapeo de autenticacion de usuario
    Usuario toEntity(AutenticarUsuarioDto autenticarUsuarioDto);


    @Mapping(target = "roles", source ="roles", qualifiedByName /*{nombre del metodo devuelto por defecto}*/ = "mapRoles")
    RespuestaUsuarioDto usuarioToRespuestaDto(Usuario usuario);


    @Named("mapRoles")
    default Set<String> mapRoles(Set<Rol> roles){
        return roles.stream()
                .map(Rol::getNombre)
                .map(Enum::name)
                .collect(Collectors.toSet());
    }



    default Rol map(RolNombre rolNombre){
        if (rolNombre == null) return null;
        Rol rol = new Rol();
        rol.setNombre(rolNombre);
        return rol;
    }

    default RolNombre map(Rol rol){
    return rol == null ? null : rol.getNombre();
    }

}
