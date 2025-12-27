package com.co.vialogistic.sistema_gestion_logistica;



import com.co.vialogistic.sistema_gestion_logistica.service.LocalArchivoStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LocalArchivoStorageServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void guardarArchivo_deberiaGuardarEnDisco_yRetornarRutaRelativa() throws Exception {
        // Arrange

        LocalArchivoStorageService localArchivoStorageService = new LocalArchivoStorageService();
        ReflectionTestUtils.setField(localArchivoStorageService, "uploadDir", tempDir.toString());

        String subfolder = "directorio";

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "mi archivo.pdf",         // nombre original (con espacio)
                "application/pdf",
                "contenido-demo".getBytes(StandardCharsets.UTF_8)
        );


        // Act

       String relativePath =  localArchivoStorageService.guardarArchivo(file,subfolder);


        // Assert 1: retorna "subfolder/<uuid>_mi_archivo.pdf"
        assertNotNull(relativePath);
        assertTrue(relativePath.startsWith(subfolder + "/"));
        assertTrue(relativePath.endsWith("_mi_archivo.pdf"),
                "Debe reemplazar espacios por '_' y conservar extensión");

        String savedFileName = relativePath.substring((subfolder + "/").length());

        // Assert 2: el archivo existe físicamente en uploadDir/subfolder/<nombre>
        Path expectedDir = tempDir.resolve(subfolder);
        Path expectedFile = expectedDir.resolve(savedFileName);

        assertTrue(Files.isDirectory(expectedDir), "Debe crear el subfolder si no existe");
        assertTrue(Files.exists(expectedFile), "Debe guardar el archivo en disco");

        // Assert 3: contenido correcto
        String savedContent = Files.readString(expectedFile, StandardCharsets.UTF_8);
        assertEquals("contenido-demo", savedContent);
    }
}
