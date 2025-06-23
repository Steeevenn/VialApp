package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ActualizarEstadoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/archivos")
public class ArchivosAdjuntos {

     @PostMapping
    public ResponseEntity<ActualizarEstadoRecoleccionDto> actualizarArchivos(ActualizarEstadoRecoleccionDto dto) {


         return null;
     }
}
