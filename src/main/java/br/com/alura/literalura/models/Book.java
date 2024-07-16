package br.com.alura.literalura.models;

import jakarta.persistence.*;

@Entity
public class Book {

    public Book(String title, String author, String language, int downloads) {
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String language;
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
               Título: """ + title + "\n" +
                "Autor: " + author + "\n" +
                "Idioma: " + language + "\n" +
                "Downloads: " + downloads + "\n";
    
    }

}
