package com.co.vialogistic.sistema_gestion_logistica.controller;


import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearHistorialEstadoDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.EliminarEstadoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaHistorialEstadoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.service.HistorialEstadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class HistorialEstadoRecoleccion {

    private final HistorialEstadoService historialEstadoService;

    public HistorialEstadoRecoleccion( HistorialEstadoService historialEstadoService ) {
        this.historialEstadoService = historialEstadoService;
    }

 @GetMapping(value= ("/recolecciones/{recoleccionId}/historial"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RespuestaHistorialEstadoRecoleccionDto>> MostrarHistorialEstadosDeRecoleccion(@PathVariable Long recoleccionId){

             List <RespuestaHistorialEstadoRecoleccionDto> historialDto = historialEstadoService.responseHistorialEstado(recoleccionId);

           if(historialDto.isEmpty()){
               return ResponseEntity.ok(Collections.emptyList());
           }

           return ResponseEntity.ok(historialDto);
    }

    @DeleteMapping("estado/{estadoId}/eliminar/recolecciones/{recoleccionId}")
    public ResponseEntity<Void> eliminarEstadoRecoleccion(@PathVariable Long recoleccionId, @PathVariable Long estadoId){


        historialEstadoService.eliminarEstadoRecoleccion(recoleccionId, estadoId);

        return ResponseEntity.noContent().build();



    }
 }
