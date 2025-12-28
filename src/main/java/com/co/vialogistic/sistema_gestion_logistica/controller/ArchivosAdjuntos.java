package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.ArchivosAdjuntosDto;
import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.ArchivoAdjuntoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.ArchivoAdjuntoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoArchivo;
import com.co.vialogistic.sistema_gestion_logistica.repository.ArchivoAdjuntosRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.GestionarArchivosAdjuntos;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/archivos")
@Tag(name = "ArchivosAdjuntos", description = " Endpoint que getiona la forma en que un domiciliario puede adjuntar un archvio bien sea una prueba de entrega o recogida a su recoleccion asignada de forma ordenada y limpia")
public class ArchivosAdjuntos {

    private final GestionarArchivosAdjuntos gestionarArchivosAdjuntos;


    public ArchivosAdjuntos( GestionarArchivosAdjuntos gestionarArchivosAdjuntos) {
        this.gestionarArchivosAdjuntos = gestionarArchivosAdjuntos;
    }

     @PutMapping("/adjuntarArchivo")
    public ResponseEntity<String> actualizarArchivos(ArchivosAdjuntosDto archvivosAdjuntosDto, MultipartFile archivo) {

//Llamada a servicio para generar los archvios en local y GUARDAR LA REFERENCIA EN LA BASE DE DATOS

        gestionarArchivosAdjuntos.AgregarArchivosAdjuntosARecoleccion(
                 archvivosAdjuntosDto.recoleccionId(),
                 archivo,
                 TipoArchivo.FOTO_PAQUETE,
                 archvivosAdjuntosDto.domiciliarioQueSube(),
                 archvivosAdjuntosDto.notasDomiciliario()
         );
         return ResponseEntity.status(HttpStatus.CREATED).body("Archivo adjunto agregado correctamente");
     }

   @GetMapping("/archivosAdjuntos/{recoleccionId}")
    public ResponseEntity<List<ArchivoAdjuntoRecoleccionDto>> listarArchivos (@PathVariable("recoleccionId") Long recoleccionId){

       List <ArchivoAdjuntoRecoleccionDto> archivos = gestionarArchivosAdjuntos.obtenerArchivosPorRecoleccion(recoleccionId);

       if(archivos.isEmpty()){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(archivos);
   }


   /*Este endpoint se encarga de visualizar el archivo que cada usuario sube se busca por recoleccion y se muestra los archvios adjuntos */
    @GetMapping("/verArchivosAdjuntos/{archivoId}")
   public ResponseEntity<Resource> verArchivosAdjuntos(@PathVariable("archivoId") Long archivoId ){

        ArchivoAdjuntoRecoleccionDto dto = gestionarArchivosAdjuntos.obtenerArchivoPorId(archivoId);

        if( dto == null || dto.urlArchivo() == null || dto.urlArchivo().isEmpty() ){
                return ResponseEntity.notFound().build();
        }


        // Construye la URL pública local a /files/** usando la urlArchivo guardada

        URI publicUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(dto.urlArchivo())
                .build()
                .toUri();


        // 302 Found: Postman/navegador “saltan” a /files/... y lo muestran
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(publicUrl)
                .build();

   }

}
