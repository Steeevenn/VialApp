package com.co.vialogistic.sistema_gestion_logistica.service;


import com.co.vialogistic.sistema_gestion_logistica.dto.respuestas.ArchivoAdjuntoRecoleccionDto;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.ArchivoAdjuntoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoArchivo;
import com.co.vialogistic.sistema_gestion_logistica.repository.ArchivoAdjuntosRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GestionarArchivosAdjuntos {

    private final RecoleccionRepository recoleccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalArchivoStorageService localArchivoStorageService;
    private final ArchivoAdjuntosRepository archivoAdjuntosRepository;

    public GestionarArchivosAdjuntos(RecoleccionRepository recoleccionRepository, UsuarioRepository usuarioRepository, LocalArchivoStorageService localArchivoStorageService, ArchivoAdjuntosRepository archivoAdjuntosRepository) {
        this.recoleccionRepository = recoleccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.localArchivoStorageService = localArchivoStorageService;
        this.archivoAdjuntosRepository = archivoAdjuntosRepository;
    }


public void     AgregarArchivosAdjuntosARecoleccion(Long recoleccionId, MultipartFile file, TipoArchivo tipoArchivo, @NotBlank Long domiciliarioQueSube, String notasDomiciliario){

        // 1. Buscar la recolección directamente por su ID ()
    Recoleccion recoleccion = recoleccionRepository.findById(recoleccionId)
            .orElseThrow(() -> new RuntimeException("Recolección no encontrada con id: " + recoleccionId));


    if (recoleccion.getDomiciliarioAsginado() == null || !recoleccion.getDomiciliarioAsginado().getId().equals(domiciliarioQueSube)) {
        throw new RuntimeException("Acción no autorizada. Esta recolección no está asignada a usted.");
    }

    //Usuario que subio los archvivos adjuntos de una recoleccion
    Optional<Usuario> usuarioQueSube  = usuarioRepository.findById(domiciliarioQueSube);

    // 3. Guardar el archivo físico usando nuestro servicio de almacenamiento
    // Se guarda en una subcarpeta "recolecciones" para mantener el orden.
    String urlArchivo = localArchivoStorageService.guardarArchivo (file, "recolecciones_");



    // validar notas del domiciliario

    if (notasDomiciliario != null && !notasDomiciliario.trim().isEmpty()){

        String notasActuales = recoleccion.getNotasDomiciliario();
        if (notasActuales == null) {
            notasActuales = "";
        }

        String nuevasNotas = notasActuales + "\n" + OffsetDateTime.now() + ":" + notasDomiciliario;

        recoleccion.setNotasDomiciliario(nuevasNotas);

    }





    // 4. Crear la entidad ArchivoAdjuntoRecoleccion con toda la información
    ArchivoAdjuntoRecoleccion nuevoAdjunto = new ArchivoAdjuntoRecoleccion();
    nuevoAdjunto.setRecoleccion(recoleccion); // Asociar con la recolección
    nuevoAdjunto.setSubidoPorUsuario(usuarioQueSube.orElseThrow()); // Quién lo subió
    nuevoAdjunto.setUrlArchivo(urlArchivo); // La URL/ruta que nos dio el servicio
    nuevoAdjunto.setTipoArchivo(tipoArchivo);
    nuevoAdjunto.setNombreOriginalArchivo(file.getOriginalFilename());
    nuevoAdjunto.setContentType(file.getContentType());
    nuevoAdjunto.setTamanoBytes(file.getSize());
    // La fecha de subida se genera automáticamente con @CreationTimestamp



    // 5. Guardar la nueva entidad en la base de datos
    // Como la relación en Recoleccion tiene CascadeType.ALL,

    ArchivoAdjuntoRecoleccion archivoGuardado =  archivoAdjuntosRepository.save(nuevoAdjunto);

    recoleccion.getArchivosAdjuntos().add(archivoGuardado);

    //recoleccionRepository.save(recoleccion);

    // Opcionalmente, si no usas Cascade, guardas el adjunto directamente:



}
    public List<ArchivoAdjuntoRecoleccionDto> obtenerArchivosPorRecoleccion(Long recoleccionId) {
        List<ArchivoAdjuntoRecoleccion> archivoRecoleccion =  archivoAdjuntosRepository.findByRecoleccionId(recoleccionId);

        return convertirADto(archivoRecoleccion);
    }

    private List<ArchivoAdjuntoRecoleccionDto> convertirADto(List<ArchivoAdjuntoRecoleccion> archivoAdjunto) {


       return  archivoAdjunto.stream()
                .map(this::mapearArchivoADto)
                .collect(Collectors.toList());

    }

    private ArchivoAdjuntoRecoleccionDto mapearArchivoADto(ArchivoAdjuntoRecoleccion archivo) {
        return new ArchivoAdjuntoRecoleccionDto(
                archivo.getId(),
                archivo.getNombreOriginalArchivo(),
                archivo.getTipoArchivo(),
                archivo.getUrlArchivo(),
                archivo.getContentType(),
                archivo.getTamanoBytes(),
                archivo.getFechaSubida(),
                archivo.getSubidoPorUsuario() != null ? archivo.getSubidoPorUsuario().getId() : null
        );
    }


}


