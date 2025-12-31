package com.co.vialogistic.sistema_gestion_logistica.service;

import com.co.vialogistic.sistema_gestion_logistica.exception.archivosAdjuntos.ArchivoAdjuntoNotSaveException;
import com.co.vialogistic.sistema_gestion_logistica.exception.archivosAdjuntos.TamanoNoValidoParaArchivoException;
import com.co.vialogistic.sistema_gestion_logistica.inferfaces.creacionales.ArchivoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class LocalArchivoStorageService implements ArchivoStorageService {

    @Value("${file.upload-dir}") // Configura esta ruta en application.properties, ej: file.upload-dir=./uploads
    private String uploadDir;

    @Override
    public String guardarArchivo(MultipartFile file, String subfolder) {

    //Validacion inicial para que pase un archivo no null si no se ejecuta el resto de la logica para evitar creacion de carpetas y demas trabajo exttra

        if(file == null || file.isEmpty()  || file.getSize() == 0){
             throw new  TamanoNoValidoParaArchivoException("Debe enviar un archivo no vacio");
        }


            // Generar un nombre de archivo único para evitar colisiones reemplazando los espacions en blanco por guion bajo para mayor claridad
            String originalFilename = StringUtils.cleanPath(
                    (Objects.requireNonNullElse(file.getOriginalFilename(), "archivo")
                            .replaceAll("\\s+", "_"))
            );
            if(originalFilename.isBlank() || originalFilename.equals(".") || originalFilename.equals("..")){
                throw new TamanoNoValidoParaArchivoException("Nombre de archivo no valido");
            }

        //A traves de la clase UUID me genera un identificador unico para incializar mis archivos con un  unico nombre
            String uniqueFilename = UUID.randomUUID() + "_" + originalFilename;

            //  Version del get deprecada validar funciionamiento
            //  Path uploadPath = Paths.get(uploadDir, subfolder);
            Path uploadPath = Path.of(uploadDir, subfolder);
           //Se crea la carpeta con la ruta marcada
            try{
                Files.createDirectories(uploadPath);}
            catch (IOException ex){
                throw new ArchivoAdjuntoNotSaveException("No se pudo guardar el archivo" + ex.getMessage());
            }
            Path filePath = uploadPath.resolve(uniqueFilename);


        try {
            // Se encarga de copiar por medio de la tuberia del getinputstream el file que llego por http a mi ruta destino que en este caso es filePaht.
              Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new ArchivoAdjuntoNotSaveException("no se puede guardar el archivo correctamente tamño del archivo no valido  " + e.getMessage());
        }

        // Devuelve una ruta relativa que puedas convertir en URL
            return subfolder + "/" + uniqueFilename;


    }
}
