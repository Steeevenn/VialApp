package com.co.vialogistic.sistema_gestion_logistica.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


/* Clase manejadora de la carpeta donde se guardan las imagenes que se suben por ejemplo de evidencia de entrega o recoleccion etc ...
* Buscamos que sea una rta publica que se pueda acceder facilemente desde el controlador o el servicio si es necesario
* */
@Configuration
public class StaticResourcesConfig implements WebMvcConfigurer {

    // Usa ./uploads por defecto (carpeta del proyecto). Puedes cambiarlo por propiedad.
    @Value("${app.uploads-dir:./uploads}")
    private String uploadsDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path base = Paths.get(uploadsDir).toAbsolutePath().normalize();

        // MUY IMPORTANTE: prefijo file: y terminar con "/"
        String location = "file:" + base + "/";

        registry.addResourceHandler("/files/**")
                .addResourceLocations(location)
                .setCachePeriod(3600);

        // Debug opcional en logs para verificar la ruta real:
        System.out.println("[StaticResources] Serving /files/** from: " + location);
    }
}
