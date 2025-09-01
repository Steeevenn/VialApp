package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.ArchivosAdjuntosDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.ArchivoAdjuntoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.ArchivoAdjuntoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoArchivo;
import com.co.vialogistic.sistema_gestion_logistica.repository.ArchivoAdjuntosRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.GestionarArchivosAdjuntos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/archivos")
public class ArchivosAdjuntos {

    private final GestionarArchivosAdjuntos gestionarArchivosAdjuntos;


    public ArchivosAdjuntos( GestionarArchivosAdjuntos gestionarArchivosAdjuntos) {
        this.gestionarArchivosAdjuntos = gestionarArchivosAdjuntos;
    }

     @PutMapping("/adjuntarArchivo")
    public ResponseEntity<String> actualizarArchivos(ArchivosAdjuntosDto archvivosAdjuntosDto, MultipartFile archivo) {

//Llamada a servicio para generar los archvios en local y guardar la referencia a la base de datos

        gestionarArchivosAdjuntos.AgregarArchivosAdjuntosARecoleccion(
                 archvivosAdjuntosDto.recoleccionId(),
                 archivo,
                 TipoArchivo.FOTO_PAQUETE,
                 archvivosAdjuntosDto.domiciliarioQueSube(),
                 archvivosAdjuntosDto.notasDomiciliario()
         );
         return ResponseEntity.status(HttpStatus.CREATED).body("Archivo adjunto creado correctamente");
     }

   @GetMapping("/archivosAdjuntos/{recoleccionId}")
    public ResponseEntity<List<ArchivoAdjuntoRecoleccionDto>> listarArchivos (@PathVariable("recoleccionId") Long recoleccionId){

       List <ArchivoAdjuntoRecoleccionDto> archivos = gestionarArchivosAdjuntos.obtenerArchivosPorRecoleccion(recoleccionId);

       if(archivos.isEmpty()){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(archivos);



   }




}
