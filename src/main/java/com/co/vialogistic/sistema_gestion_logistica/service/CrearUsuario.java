package com.co.vialogistic.sistema_gestion_logistica.service;
import com.co.vialogistic.sistema_gestion_logistica.exception.RolNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.dto.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Rol;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales.CreacionDeUsuario;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.UsuarioMapper;
import com.co.vialogistic.sistema_gestion_logistica.repository.*;
import jakarta.transaction.Transactional;
//security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CrearUsuario implements CreacionDeUsuario {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper; // Mapper inyectado
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public CrearUsuario(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // métodos para crear usuarios, por ejemplo:



    @Transactional
    @Override
    public Usuario crearUsuario(CrearUsuarioDto crearUsuarioDto) {
            //1. passwoord debe ser hasheada antes de guardarla
            String passwordHash = passwordEncoder.encode(crearUsuarioDto.password());

            // 2. Convertir roles (BUSCAR existentes, no crear nuevos)
             Set<Rol>roles = crearUsuarioDto.roles().stream()
            .map(rolNombre -> roleRepository.findByNombre(rolNombre)
                            .orElseThrow(() -> new RolNotFoundException(rolNombre)))

            .collect(Collectors.toSet());

             //Creacion del usuario medianete el obj Mapper
             Usuario usuario = usuarioMapper.toEntity(crearUsuarioDto);
             usuario.setPasswordHash(passwordHash);
             usuario.setEmail(crearUsuarioDto.email());
             usuario.setNombreCompleto(crearUsuarioDto.nombreCompleto());
             usuario.setTelefono(crearUsuarioDto.telefono());
             usuario.setRoles(roles);

        usuarioRepository.save(usuario);
        return usuario;
    }
}
