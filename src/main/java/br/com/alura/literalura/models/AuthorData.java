package br.com.alura.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorData(
        @JsonAlias("name") String name,
        @JsonAlias("birth_date") String birth_date,
        @JsonAlias("death_date") String death_date) {
}
