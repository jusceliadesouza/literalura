package br.com.alura.literalura.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {

    public Author(String name, String birth_date, String death_date) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birth_date;
    private String death_date;

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDeathDate() {
        return death_date;
    }

    public void setDeathDate(String death_date) {
        this.death_date = death_date;
    }

    @Override
    public String toString() {
        return "Autor [name=" + name + ", birth_date=" + birth_date + ", death_date=" + death_date + "]";
    }
}
