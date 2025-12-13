package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.*;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.*;
import com.co.vialogistic.sistema_gestion_logistica.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/v1")
public class Usuarios {

    private final CrearUsuario crearUsuarioService;
    private final RespuestaUsuarioAlCrear respuestaUsuarioAlCrear;
    private final ListarUsuarios listarUsuariosService;
    private final EliminarUsuarios eliminarUsuarioService;

    public Usuarios(CrearUsuario crearUsuarioService, RespuestaUsuarioAlCrear respuestaUsuarioAlCrear, ListarUsuarios listarUsuariosService, EliminarUsuarios eliminarUsuarioService) {
        this.crearUsuarioService = crearUsuarioService;
        this.respuestaUsuarioAlCrear = respuestaUsuarioAlCrear;
        this.listarUsuariosService = listarUsuariosService;
        this.eliminarUsuarioService = eliminarUsuarioService;
    }

    @PostMapping("/usuarios/registrar")
    public ResponseEntity<RespuestaCreacionUsuarioDto> registrarUsuario(@RequestBody @Valid CrearUsuarioDto crearUsuarioDto) {
        //Intento de creacion de usuario

        // Crear el usuario y obtener la entidad creada
        Usuario usuarioCreado = crearUsuarioService.crearUsuario(crearUsuarioDto);

        RespuestaCreacionUsuarioDto respuesta = respuestaUsuarioAlCrear.construirRespuestaUsuario(
                usuarioCreado, HttpStatus.CREATED, "Usuario creado exitosamente", null);


        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

    }


    @GetMapping("/usuarios/listar")
    public ResponseEntity<List<RespuestaListarUsuariosDto>> totalUsuarios() {
        List<RespuestaListarUsuariosDto> usuarios;

        usuarios = listarUsuariosService.obtenerTodosLosUsuarios();


        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }


    @DeleteMapping("/usuarios/deleteById/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") Long idUsuario) {


        eliminarUsuarioService.eliminarUsuarios(idUsuario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();



    }
}
