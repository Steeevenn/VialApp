package com.co.vialogistic.sistema_gestion_logistica.controller;

import com.co.vialogistic.sistema_gestion_logistica.dto.creacionales.ArchivosAdjuntosDto;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales.ArchivoStorageService;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoArchivo;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.service.AgregarArchivosAdjuntos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/archivos")
public class ArchivosAdjuntos {

    private final AgregarArchivosAdjuntos agregarArchivosAdjuntos;

    public ArchivosAdjuntos( AgregarArchivosAdjuntos agregarArchivosAdjuntos) {
        this.agregarArchivosAdjuntos = agregarArchivosAdjuntos;
    }

     @PutMapping("/adjuntarArchivo")
    public ResponseEntity<String> actualizarArchivos(ArchivosAdjuntosDto archvivosAdjuntosDto, MultipartFile archivo) {

//Llamada a servicio para generar los archvios en local y guardar la referencia a la base de datos

         agregarArchivosAdjuntos.AgregarArchivosAdjuntosARecoleccion(
                 archvivosAdjuntosDto.recoleccionId(),
                 archivo, TipoArchivo.FOTO_PAQUETE,
                 archvivosAdjuntosDto.domiciliarioQueSube()
         );
         return ResponseEntity.status(HttpStatus.CREATED).body("Archivo adjunto creado correctamente");
     }


}
