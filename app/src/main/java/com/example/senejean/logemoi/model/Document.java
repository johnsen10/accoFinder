package com.example.senejean.logemoi.model;

public class Document {

    private String id_document;
    private String mimeType;
    private String url;

    public Document(String id_document, String mimeType, String url) {
        this.id_document = id_document;
        this.mimeType = mimeType;
        this.url = url;
    }
}
