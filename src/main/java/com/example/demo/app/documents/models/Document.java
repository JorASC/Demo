package com.example.demo.app.documents.models;

import javax.persistence.*;

@Entity
@Table(name = "adm_document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adm_document_seq_gen")
    @SequenceGenerator(name = "adm_document_seq_gen", sequenceName = "adm_document_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "path", columnDefinition = "varchar(255)", nullable = false)
    private String path;
    @Column(name = "file_name", columnDefinition = "varchar(255)", nullable = false)
    private String fileName;

    public Document() {
    }

    public Document(Integer id, String path, String fileName) {
        this.id = id;
        this.path = path;
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
