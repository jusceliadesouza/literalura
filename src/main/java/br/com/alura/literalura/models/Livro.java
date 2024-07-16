package br.com.alura.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Livro {
    private String title;
    @JsonAlias("author_name")
    private String author;
    private String language;
    @JsonAlias("download_count")
    private int downloads;

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return """
               ----- Livro -----
               T\u00edtulo: """ + title + "\n" +
                "Autor: " + author + "\n" +
                "Idioma: " + language + "\n" +
                "Downloads: " + downloads + "\n";
    
    }

}
