package com.co.vialogistic.sistema_gestion_logistica.controller;


import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearHistorialEstadoDto;
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
/*
    @PostMapping("recolecciones/{recoleccionId}/historial/estados")
    public ResponseEntity<RespuestaHistorialEstadoRecoleccionDto> CrearHistorialEstadosRecoleccion( @PathVariable Long recoleccionId,
                                                                                                   @Valid @RequestBody CrearHistorialEstadoDto dto){

        //Validar que el idRecoleccion sea coherente en el parametro del metodo y lo que llega desde fuera
        if(!Objects.equals(recoleccionId, dto.recoleccionId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La recoleccionId de la ruta no coincide con el id del cuerpo ");
        }


        RespuestaHistorialEstadoRecoleccionDto rep = historialEstadoService.creacionHistorialEstado(dto);

        //Obejeto uri con la direccion del recurso creado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{eventoId}")
                .buildAndExpand(rep.id())
                .toUri();

        return ResponseEntity.created(location).body(rep);

*/

 @GetMapping(value= ("/recolecciones/{recoleccionId}/historial"), produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RespuestaHistorialEstadoRecoleccionDto>> MostrarHistorialEstadosDeRecoleccion(@PathVariable Long recoleccionId){

             List <RespuestaHistorialEstadoRecoleccionDto> historialDto = historialEstadoService.responseHistorialEstado(recoleccionId);

           if(historialDto.isEmpty()){
               return ResponseEntity.ok(Collections.emptyList());
           }

           return ResponseEntity.ok(historialDto);
    }



}
