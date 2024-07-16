package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alura.literalura.models.Author;
import br.com.alura.literalura.models.Book;
import br.com.alura.literalura.models.BookData;
import br.com.alura.literalura.models.AuthorData;

import java.util.List;
import java.util.stream.Collectors;

// Mapear a resposta JSON para objetos Java utilizando o Jackson
public class ConvertData implements IConvertData {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Book getBookData(String json) {
        try {
            BookData bookData = mapper.readValue(json, BookData.class);
            return new Book(bookData.title(), bookData.author(), bookData.language(), bookData.downloads());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Author getAuthorData(String json) {
        try {
            AuthorData authorData = mapper.readValue(json, AuthorData.class);
            return new Author(authorData.name(), authorData.birth_date(), authorData.death_date());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> getBooksFromJson(String json) {
        try {
            return mapper.readTree(json)
                    .path("results")
                    .findValuesAsText("books")
                    .stream()
                    .map(bookJson -> getBookData(bookJson))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Author> getAuthorsFromJson(String json) {
        try {
            return mapper.readTree(json)
                    .path("results")
                    .findValuesAsText("authors")
                    .stream()
                    .map(authorJson -> getAuthorData(authorJson))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
