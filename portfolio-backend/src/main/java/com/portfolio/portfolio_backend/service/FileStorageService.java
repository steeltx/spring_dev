package com.portfolio.portfolio_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo esta vacio");
        }
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if(originalFilename!=null){
            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex > 0){
                extension = originalFilename.substring(dotIndex);
            }
        }
        String filename = UUID.randomUUID().toString() + extension;
        // crear la ruta
        Path filePath = Paths.get(uploadDir, filename).normalize();
        // copia del archivo
        Files.copy(file.getInputStream(), filePath);
        // regresar la url relativa
        return "/img/projects/"+filename;

    }

}
