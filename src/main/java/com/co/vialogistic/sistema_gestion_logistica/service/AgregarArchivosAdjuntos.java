package com.co.vialogistic.sistema_gestion_logistica.service;


import com.co.vialogistic.sistema_gestion_logistica.model.entity.ArchivoAdjuntoRecoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Recoleccion;
import com.co.vialogistic.sistema_gestion_logistica.model.entity.Usuario;
import com.co.vialogistic.sistema_gestion_logistica.model.enums.TipoArchivo;
import com.co.vialogistic.sistema_gestion_logistica.repository.RecoleccionRepository;
import com.co.vialogistic.sistema_gestion_logistica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
public class AgregarArchivosAdjuntos {

    private final RecoleccionRepository recoleccionRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalArchivoStorageService localArchivoStorageService;

    public AgregarArchivosAdjuntos(RecoleccionRepository recoleccionRepository, UsuarioRepository usuarioRepository, LocalArchivoStorageService localArchivoStorageService) {
        this.recoleccionRepository = recoleccionRepository;
        this.usuarioRepository = usuarioRepository;
        this.localArchivoStorageService = localArchivoStorageService;
    }


public void AgregarArchivosAdjuntosARecoleccion(Long recoleccionId, MultipartFile file, TipoArchivo tipoArchivo, @NotBlank Long domiciliarioQueSube){

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
    recoleccion.getArchivosAdjuntos().add(nuevoAdjunto);
    recoleccionRepository.save(recoleccion);

    // Opcionalmente, si no usas Cascade, guardas el adjunto directamente:
    // arc hivoAdjuntoRepository.save(nuevoAdjunto);


}

}
