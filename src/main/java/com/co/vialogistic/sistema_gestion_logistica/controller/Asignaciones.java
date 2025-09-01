package com.co.vialogistic.sistema_gestion_logistica.controller;


import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.service.GestionDeRecolecciones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asignaciones")
public class Asignaciones{

    private final GestionDeRecolecciones asignarRecoleccionAUsuario;


    public Asignaciones(GestionDeRecolecciones asignarRecoleccionAUsuario) {
        this.asignarRecoleccionAUsuario = asignarRecoleccionAUsuario;
    }
//Controlador que asigna un domiciliario a un id de recoleccion especifica

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarRecolecciones(
            @RequestParam Long idUsuarioAsignador,
            @RequestParam Long idDomiciliario,
            @RequestParam Long idRecoleccion
    )

    {
        asignarRecoleccionAUsuario.AsignarRecoleccionAUsuario(idUsuarioAsignador, idDomiciliario, idRecoleccion);
            return ResponseEntity.ok().build();
        }



}