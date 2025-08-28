package com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales;

import org.springframework.web.multipart.MultipartFile;

public interface ArchivoStorageService {
    /**
     * Guarda un archivo y devuelve su URL o identificador de acceso.
     * @param file El archivo a guardar.
     * @param subfolder Una carpeta específica para organizar (ej. "recolecciones").
     * @return La URL pública o la ruta donde se puede acceder al archivo.
     */
    String guardarArchivo(MultipartFile file, String subfolder);
}


