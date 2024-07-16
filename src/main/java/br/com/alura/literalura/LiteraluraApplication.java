package br.com.alura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.literalura.main.App;

import br.com.alura.literalura.service.BookService;

import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;
	private AuthorRepository authorRepository;
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App app = new App(bookRepository, authorRepository, bookService);
		app.start();
	}
}
