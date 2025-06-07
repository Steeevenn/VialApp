package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.CrearUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.RespuestaUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.exception.RolNotFoundException;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.*;
import com.co.vialogistic.sistema_gestion_logistica.service.CrearUsuario;
import com.co.vialogistic.sistema_gestion_logistica.service.RespuestaUsuarioAlCrear;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1")
public class Usuarios  {

    private final CrearUsuario crearUsuarioService;
    private final RespuestaUsuarioAlCrear respuestaUsuarioAlCrear;

    public Usuarios(CrearUsuario crearUsuarioService, RespuestaUsuarioAlCrear respuestaUsuarioAlCrear) {
        this.crearUsuarioService = crearUsuarioService;
        this.respuestaUsuarioAlCrear = respuestaUsuarioAlCrear;
    }

    @PostMapping("/usuarios/registrar")
    public ResponseEntity<RespuestaUsuarioDto> registrarUsuario (@RequestBody @Valid CrearUsuarioDto crearUsuarioDto) {
        //Intento de creacion de usuario
        try {

            // Crear el usuario y obtener la entidad creada
            Usuario usuarioCreado = crearUsuarioService.crearUsuario(crearUsuarioDto);

            RespuestaUsuarioDto respuesta = respuestaUsuarioAlCrear.construirRespuestaUsuario(
                    usuarioCreado, HttpStatus.CREATED, "Usuario creado exitosamente", null);

            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (RolNotFoundException e) {
            RespuestaUsuarioDto respuesta = respuestaUsuarioAlCrear.construirRespuestaError(
                    crearUsuarioDto.username(),
                    crearUsuarioDto.email(),
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    "Rol no valido"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        } catch (Exception e) {
            RespuestaUsuarioDto respuesta = respuestaUsuarioAlCrear.construirRespuestaError(
                    crearUsuarioDto.username(),
                    crearUsuarioDto.email(),
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    "Error interno del servidor"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);

        }
    }

}
