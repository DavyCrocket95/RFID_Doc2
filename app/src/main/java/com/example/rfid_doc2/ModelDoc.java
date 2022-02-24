package com.example.rfid_doc2;

public class ModelDoc {
    private String doc_titre;
    private String doc_format;
    private String doc_categorie;
    private String doc_auteur;
    private String archive;
    private String resume;
    private String contenuDoc;
    private String doc_IdProd;

    public ModelDoc() {
    }

    public ModelDoc(String doc_titre, String doc_format, String doc_categorie, String doc_auteur, String archive, String resume, String contenuDoc, String doc_IdProd) {
        this.doc_titre = doc_titre;
        this.doc_format = doc_format;
        this.doc_categorie = doc_categorie;
        this.doc_auteur = doc_auteur;
        this.archive = archive;
        this.resume = resume;
        this.contenuDoc = contenuDoc;
        this.doc_IdProd = doc_IdProd;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getContenuDoc() {
        return contenuDoc;
    }

    public void setContenuDoc(String contenuDoc) {
        this.contenuDoc = contenuDoc;
    }

    public String getDoc_IdProd() {
        return doc_IdProd;
    }

    public void setDoc_IdProd(String doc_IdProd) {
        this.doc_IdProd = doc_IdProd;
    }


    public String getDoc_titre() {
        return doc_titre;
    }

    public void setDoc_titre(String doc_titre) {
        this.doc_titre = doc_titre;
    }

    public String getDoc_format() {
        return doc_format;
    }

    public void setDoc_format(String doc_format) {
        this.doc_format = doc_format;
    }

    public String getDoc_categorie() {
        return doc_categorie;
    }

    public void setDoc_categorie(String doc_categorie) {
        this.doc_categorie = doc_categorie;
    }

    public String getDoc_auteur() {
        return doc_auteur;
    }

    public void setDoc_auteur(String doc_auteur) {
        this.doc_auteur = doc_auteur;
    }
}


