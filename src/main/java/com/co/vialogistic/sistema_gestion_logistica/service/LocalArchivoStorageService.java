package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales.ArchivoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class LocalArchivoStorageService implements ArchivoStorageService {

    @Value("${file.upload-dir}") // Configura esta ruta en application.properties, ej: file.upload-dir=./uploads
    private String uploadDir;

    @Override
    public String guardarArchivo(MultipartFile file, String subfolder) {
        try {
            // Generar un nombre de archivo único para evitar colisiones
            String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename().replaceAll("\\s+", "_")));
            String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

            Path uploadPath = Paths.get(uploadDir, subfolder);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Devuelve una ruta relativa que puedas convertir en URL
            return subfolder + "/" + uniqueFilename;
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo guardar el archivo. Error: " + ex.getMessage());
        }
    }
}
