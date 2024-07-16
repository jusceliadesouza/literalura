package br.com.alura.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title") String title,
        @JsonAlias("author") String author,
        @JsonAlias("language") String language,
        @JsonAlias("downloads") int downloads) {

}
