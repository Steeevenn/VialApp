package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.model.dto.*;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class Usuarios  {




    @PostMapping("/usuarios/registrar")
    public ResponseEntity<RespuestaUsuarioDto> registrarUsuario (Usuario usuario) {
        // Lógica para registrar un usuario


        return null ;
    }
}
