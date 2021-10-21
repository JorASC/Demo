package com.example.demo.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Componente para subir y descargar archivos del servidor
 *
 * @author alfonso
 * @since oct-11-2021
 */
@Component
public class FileManager {

    @Value("${bucket}")
    private String bucket;

    /**
     * Método para subir un archivo al servidor
     *
     * @param multipartFile
     * @return
     */
    public String upload(MultipartFile multipartFile) throws IOException {

        File newFile = new File(bucket + File.separator + multipartFile.getOriginalFilename());
        Files.copy(multipartFile.getInputStream(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return newFile.getName();

    }

    public ResponseEntity<Resource> download(String fullPathFileName) throws IOException {
        Path path = Paths.get(bucket + File.separator + fullPathFileName).normalize();
        String contentType = Files.probeContentType(new File(path.toString()).toPath());
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                resource.getFilename().replace("%20", " ") +
                                "\""
                )
                .body(resource);
    }

}
