package com.unicauca.cfiet.solicitudes.infraestructura.configuracion.lectorArchivos.almacenador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Service
public class AlmacenadorArchivos {
    @Value("${app.uploads.base-path}")
    private String basePath;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    public String getBasePath() {
        return basePath;
    }

    /**
     * Guarda el archivo en basePath/uuidSolicitud/nombreAnexo_fecha.ext
     * @param uuidSolicitud UUID de la solicitud (para crear carpeta)
     * @param archivo MultipartFile a guardar
     * @param nombreAnexo Nombre del anexo que se usará como prefijo
     * @return ruta completa del archivo guardado
     */
    public String guardarArchivo(String uuidSolicitud, MultipartFile archivo, String nombreAnexo) throws IOException {
        // Crear carpeta de la solicitud
        File carpetaSolicitud = new File(basePath + "/" + uuidSolicitud);
        if (!carpetaSolicitud.exists()) carpetaSolicitud.mkdirs();

        // Obtener extensión del archivo original
        String extension = "";
        String originalName = archivo.getOriginalFilename();
        if (originalName != null && originalName.contains("."))
            extension = originalName.substring(originalName.lastIndexOf("."));

        // Normalizar el nombre del anexo: quitar espacios y caracteres problemáticos
        String nombreSeguro = nombreAnexo.trim()
                .replaceAll("\\s+", "_")           // reemplaza espacios por _
                .replaceAll("[^a-zA-Z0-9_\\-]", ""); // elimina caracteres especiales

        // Fecha actual para el nombre
        String fecha = LocalDateTime.now().format(FORMATTER);

        // Nombre final del archivo = nombreSeguro_fecha.ext
        File destino = new File(carpetaSolicitud, nombreSeguro + "_" + fecha + extension);

        // Guardar archivo en disco
        archivo.transferTo(destino);

        // Retornar URL pública para guardar en DB
        String urlPublica = "/api/anexos/" + uuidSolicitud + "/" + destino.getName();
        return urlPublica;
    }
}
