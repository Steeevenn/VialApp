package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearHistorialEstadoDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaHistorialEstadoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.RespuestaListarRecoleccionesDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.CrearRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.wrappers.RespuestaTotalRecoleccionesUsuarioDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.mapeadores.RecoleccionMapper;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.EstadoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class Recoleccciones {

    private final CrearRecoleccion crearRecoleccion;
    private final RecoleccionRepository recoleccionRepository;
    private final RecoleccionMapper recoleccionMapper;
    private final GestionDeRecolecciones gestionDeRecolecciones;
    private final HistorialEstadoService historialEstadoService;
    private final RespuestaListarRecoleccion respuestaListarRecoleccion;

    public Recoleccciones(CrearRecoleccion crearRecoleccion, RecoleccionRepository recoleccionRepository, RecoleccionMapper recoleccionMapper, GestionDeRecolecciones gestionDeRecolecciones, HistorialEstadoService historialEstadoService, RespuestaListarRecoleccion respuestaListarRecoleccion) {
        this.crearRecoleccion = crearRecoleccion;
        this.recoleccionRepository = recoleccionRepository;
        this.recoleccionMapper = recoleccionMapper;
        this.gestionDeRecolecciones = gestionDeRecolecciones;
        this.historialEstadoService = historialEstadoService;
        this.respuestaListarRecoleccion = respuestaListarRecoleccion;
    }


    @PostMapping("/recolecciones/crear")
    //public ResponseEntity<ActualizarEstadoRecoleccionDto> crearRecoleccion(@RequestBody @Valid CrearRecoleccionDto crearRecoleccionDto) {
    public ResponseEntity<CrearRecoleccionDto> crearRecoleccion(@RequestBody @Valid CrearRecoleccionDto crearRecoleccionDto) {

        CrearRecoleccionDto recoleccionCreada = recoleccionMapper.toActualizarRecolecciones(crearRecoleccion.crearRecoleccion(crearRecoleccionDto));

        if(!(recoleccionCreada == null)){
            return ResponseEntity.status(201).body(recoleccionCreada);
        }else{
                return  ResponseEntity.internalServerError().body(recoleccionCreada);
        }

    }

    @GetMapping("/recolecciones/listarRecolecciones")
    public ResponseEntity<RespuestaTotalRecoleccionesUsuarioDto> listarRecolecciones() {

        List<RespuestaListarRecoleccionesDto> recolecionesAll = gestionDeRecolecciones.listarRecoleccionesAll();

        RespuestaTotalRecoleccionesUsuarioDto response = new RespuestaTotalRecoleccionesUsuarioDto(
                recolecionesAll
        );

        return ResponseEntity.ok(response);

    }
//Controlador para listar el total de reccolecciones que un usuario agendo para posteriormente mostrar o devolver para asginar cada recoleccion

    @GetMapping("/listarRecolecciones/usuario")
    public ResponseEntity<List<RespuestaListarRecoleccionesDto>> listarRecolecciones(@RequestParam Long idUsuarioAsignador){
        List<RespuestaListarRecoleccionesDto> recolecciones = gestionDeRecolecciones.ListaRecolecciones
                (idUsuarioAsignador, EstadoRecoleccion.PENDIENTE_ASIGNACION);

        return ResponseEntity.status(200).body(recolecciones);

    }


/* endpoint para modificar estado de las recolecciones y que automaticamente se suban los estados a estados de recoleccion*/
    @PutMapping("recolecciones/{recoleccionId}/modificar/estado")
    public ResponseEntity<RespuestaHistorialEstadoRecoleccionDto> modificarEstadosRecoleccion (@PathVariable Long recoleccionId,
             @Valid @RequestBody CrearHistorialEstadoDto crearHistorialEstadoDto){
        //Validar que el idRecoleccion sea coherente en el parametro del metodo y lo que llega desde fuera
        if(!Objects.equals(recoleccionId, crearHistorialEstadoDto.recoleccionId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La recoleccionId de la ruta no coincide con el id del cuerpo ");
        }

        RespuestaHistorialEstadoRecoleccionDto rep = historialEstadoService.creacionHistorialEstado(crearHistorialEstadoDto);

        //Obejeto uri con la direccion del recurso creado
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{eventoId}")
                .buildAndExpand(rep.id())
                .toUri();

        return ResponseEntity.created(location).body(rep);

    }




}
