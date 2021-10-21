package com.example.demo.app.documents.services;

import com.example.demo.app.documents.dtos.DocumentDto;
import com.example.demo.app.documents.models.Document;
import com.example.demo.app.documents.repos.DocumentRepository;
import com.example.demo.components.FileManager;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final FileManager fileManager;

    public DocumentService(DocumentRepository documentRepository, FileManager fileManager) {
        this.documentRepository = documentRepository;
        this.fileManager = fileManager;
    }

    /**
     * Recibe un mulitpartfile y lo envía al FileManager para cargarlo en el FileSystem
     * @param multipartFile
     * @return
     */
    public Integer save(MultipartFile multipartFile) throws IOException {
        String filePath = fileManager.upload(multipartFile);
        DocumentDto documentDto = new DocumentDto();
        documentDto.setPath(filePath);
        documentDto.setFileName(multipartFile.getOriginalFilename());
        return documentRepository.save(documentDto.toEntity()).getId();
    }

    /**
     * Devuelve el archivo, con base en el id
     * @param id
     * @return
     * @throws IOException
     */
    public ResponseEntity<Resource> download(Integer id) throws IOException {
        return fileManager.download(documentRepository.getById(id).getFileName());
    }
}
