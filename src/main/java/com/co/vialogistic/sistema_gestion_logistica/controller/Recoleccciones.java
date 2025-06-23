package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.actualizaciones.ActualizarEstadoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.DireccionesMapper;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class Recoleccciones {

    private final CrearRecoleccion crearRecoleccion;
    private final RecoleccionRepository recoleccionRepository;
    private final RecoleccionMapper recoleccionMapper;

    public Recoleccciones(CrearRecoleccion crearRecoleccion, RecoleccionRepository recoleccionRepository, RecoleccionMapper recoleccionMapper) {
        this.crearRecoleccion = crearRecoleccion;
        this.recoleccionRepository = recoleccionRepository;
        this.recoleccionMapper = recoleccionMapper;

    }


    @PostMapping("/recolecciones/crear")
    public ResponseEntity<ActualizarEstadoRecoleccionDto> crearRecoleccion(@RequestBody @Valid CrearRecoleccionDto crearRecoleccionDto) {

        // Debug completo del DTO recibido
        System.out.println("=== DEBUGGING DTO RECIBIDO ===");
        System.out.println("UsuarioAgendoId: " + crearRecoleccionDto.UsuarioAgendoId());
        System.out.println("Nombre remitente: " + crearRecoleccionDto.nombreRemitente());
        System.out.println("Teléfono remitente: " + crearRecoleccionDto.telefonoRemitente());
        System.out.println("Email remitente: " + crearRecoleccionDto.emailRemitente());

        // Debug dirección remitente
        if (crearRecoleccionDto.direccionRemitente() != null) {
            System.out.println("Dirección remitente - Tipo vía: " + crearRecoleccionDto.direccionRemitente().tipoVia());
            System.out.println("Dirección remitente - Ciudad: " + crearRecoleccionDto.direccionRemitente().ciudad());
            System.out.println("Dirección remitente - Barrio: " + crearRecoleccionDto.direccionRemitente().barrio());
        } else {
            System.out.println("Dirección remitente es NULL");
        }

        // Debug dirección destinatario
        if (crearRecoleccionDto.direccionDestinatario() != null) {
            System.out.println("Dirección destinatario - Tipo vía: " + crearRecoleccionDto.direccionDestinatario().tipoVia());
            System.out.println("Dirección destinatario - Ciudad: " + crearRecoleccionDto.direccionDestinatario().ciudad());
            System.out.println("Dirección destinatario - Barrio: " + crearRecoleccionDto.direccionDestinatario().barrio());
        } else {
            System.out.println("Dirección destinatario es NULL");
        }

        System.out.println("Fecha programada: " + crearRecoleccionDto.fechaHoraProgramadaRecoleccion());
        System.out.println("Descripción paquete: " + crearRecoleccionDto.descripcionPaquete());
        System.out.println("Peso: " + crearRecoleccionDto.pesoKg());
        System.out.println("================================");

        ActualizarEstadoRecoleccionDto recoleccionCreada = null;
        try {
            recoleccionCreada = recoleccionMapper.toActualizarRecolecciones(crearRecoleccion.crearRecoleccion(crearRecoleccionDto));
          //  recoleccionCreada = crearRecoleccion.crearRecoleccion(crearRecoleccionDto);
        } catch (Exception e) {
            System.err.println("Error al crear recolección: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        if (recoleccionCreada != null) {
            System.out.println("Recolección creada exitosamente: " + recoleccionCreada.idRecoleccion());
            return ResponseEntity.status(HttpStatus.CREATED).body(recoleccionCreada);
        } else {
            System.err.println("La recolección creada es null");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/recolecciones/listarRecolecciones")
    public ResponseEntity<List<RespuestaListarRecoleccionesDto>> listarRecolecciones() {
        try {
            List<Recoleccion> recolecciones = recoleccionRepository.findAll();

            // Mapear cada entidad a DTO usando el mapper
            List<RespuestaListarRecoleccionesDto> respuestaListarRecoleccionesDtos = recolecciones.stream()
                    .map(recoleccionMapper::toDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(respuestaListarRecoleccionesDtos);
        } catch (Exception e) {
            // Manejar errores adecuadamente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }


}
