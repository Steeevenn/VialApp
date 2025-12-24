package com.co.vialogistic.sistema_gestion_logistica;


import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.UsuarioMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Rol;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.RolNombre;
import com.co.vialogistic.sistema_gestion_logistica.repository.RoleRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.CrearUsuario;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class crearUsuarioDeberiaGuardarUsuarioCuandoTodoOk {

    // 1 Inyectar el repositorio como MOCK

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UsuarioMapper usuarioMapper;


    // 2. Inyecto el Servicio como InjectMocks
    @InjectMocks
    private CrearUsuario crearUsuario;


    @Test
    void crearUsuarioOrException(){

        // Creacion de Usuario dto
        CrearUsuarioDto dto = new CrearUsuarioDto(
                "Michael01",
                "michael@gmail.com",
                "12345",
                "Michael Steven",
                "3001234567",
                Set.of(RolNombre.ADMINISTRADOR)
        );

        // 1. password hash
        when(passwordEncoder.encode("12345")).thenReturn("HASH_123");

        // 2. roles existes
        Rol role = new Rol();
        role.setNombre(RolNombre.ADMINISTRADOR);
        when(roleRepository.findByNombre(role.getNombre())).thenReturn(Optional.of(role));

        // 3. mapper convierte dto->entity
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setUsername("Michael01");
        when(usuarioMapper.toEntity(dto)).thenReturn(usuarioEntity);

        // 4. validarEmailUsuario() usa findAll()
        when(usuarioRepository.findAll()).thenReturn(List.of()); // no hay correos existentes


        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));


        Usuario result = crearUsuario.crearUsuario(dto);

        assertNotNull(result);
        assertEquals("Michael01", result.getUsername());
        assertEquals("michael@gmail.com", result.getEmail());
        assertEquals("HASH_123", result.getPasswordHash());
        assertTrue(result.getRoles().contains(role));

        verify(passwordEncoder).encode("12345");
        verify(roleRepository).findByNombre(RolNombre.ADMINISTRADOR);
        verify(usuarioMapper).toEntity(dto);
        verify(usuarioRepository).findAll();
        verify(usuarioRepository).save(any(Usuario.class));




    }




}