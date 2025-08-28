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


//Controlador para listar el total de reccolecciones que un usuario realizo para posteriormente mostrar o devolver para asginar cada recoleccion

    @GetMapping("/listarRecolecciones/usuario")
    public ResponseEntity<List<RespuestaListarRecoleccionesDto>> listarRecolecciones(@RequestParam Long idUsuarioAsignador){
        List<RespuestaListarRecoleccionesDto> recolecciones = asignarRecoleccionAUsuario.ListaRecolecciones
                (idUsuarioAsignador, EstadoRecoleccion.PENDIENTE_ASIGNACION);

        return ResponseEntity.status(200).body(recolecciones);

    }
}