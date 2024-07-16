package br.com.alura.literalura.service;

import org.springframework.stereotype.Service;

import br.com.alura.literalura.dao.DatabaseHandler;

@Service
public class BookService {

    private final DatabaseHandler dbHandler;

    public BookService() {
        this.dbHandler = new DatabaseHandler();
    }

    public void addBook(String title, String author) {
        dbHandler.insertBook(title, author);
    }
    
    public void deleteBook(int id) {
        dbHandler.deleteBook(id);
    }
}
