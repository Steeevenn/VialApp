package com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores;

import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ActualizarUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.autenticacion.AutenticarUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaCreacionUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarUsuariosDto;
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



    RespuestaCreacionUsuarioDto usuarioToRespuestaDto(Usuario usuario);


    // Mapeo a listar usuarios
    @Mapping(target = "roles", source ="roles", qualifiedByName /*{nombre del metodo devuelto por defecto}*/ = "mapRoles")
    RespuestaListarUsuariosDto usuarioToRespuestaListarDto(Usuario usuario);


    @Named("mapRoles")
    default Set<String> mapRoles(Set<Rol> roles){
        return roles.stream()
                .map(Rol::getNombre)
                .map(Enum::name)
                .collect(Collectors.toSet());

        /**
         * QUÉ HACE:
         * - Recibe: Set<Rol> (entidades Rol completas)
         * - Devuelve: Set<String> (solo los nombres de los roles como texto)
         * - Ejemplo: [Rol{id=1, nombre=ADMIN}, Rol{id=2, nombre=USER}]
         *           → ["ADMIN", "USER"]
         */
    }


    default Rol map(RolNombre rolNombre){
        if (rolNombre == null) return null;
        Rol rol = new Rol();
        rol.setNombre(rolNombre);
        return rol;
        /**
         * QUÉ HACE:
         * - Recibe: RolNombre (enum)
         * - Devuelve: Rol (entidad completa)
         * - Ejemplo: RolNombre.ADMIN → Rol{nombre=ADMIN}
         * - Usado cuando conviertes de DTO a Entity
         */
    }


    default RolNombre map(Rol rol){
    return rol == null ? null : rol.getNombre();

        /**
         * QUÉ HACE:
         * - Recibe: Rol (entidad completa)
         * - Devuelve: RolNombre (enum)
         * - Ejemplo: Rol{id=1, nombre=ADMIN} → RolNombre.ADMIN
         * - Usado cuando conviertes de Entity a DTO simple
         */
    }

}
