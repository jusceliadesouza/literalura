package br.com.alura.literalura.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.literalura.models.Author;
import br.com.alura.literalura.models.Book;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;
import br.com.alura.literalura.service.BookService;
import br.com.alura.literalura.service.ConsumeApi;
import br.com.alura.literalura.service.ConvertData;

@SpringBootApplication
public class App {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    private final String BASE_URL = "https://gutendex.com/books/";

    public App(BookRepository bookRepository, AuthorRepository authorRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        ConsumeApi consumeApi = new ConsumeApi();
        ConvertData convertData = new ConvertData();

        while (true) {
            System.out.println("Bem-vindo ao LiterAlura! Escolha uma opção:\n");
            System.out.println("1. Buscar livro por título");
            System.out.println("2. Listar livros registrados ");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos em determinado ano");
            System.out.println("5. Listar autores em determinado idioma");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Digite o título do livro:");
                    String title = scanner.nextLine();
                    searchBookByTitle(title, consumeApi, convertData);
                }
                case 2 ->
                    listAllBooks();
                case 3 ->
                    listAuthors();
                case 4 -> {
                    System.out.println("Digite o ano:");
                    int year = scanner.nextInt();
                    listLivingAuthorsByYear(year);
                }
                case 5 -> {
                    System.out.println("Digite o idioma:");
                    String language = scanner.nextLine();
                    listAuthorsByLanguage(language);
                }
                case 0 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                }
                default ->
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void searchBookByTitle(String title, ConsumeApi consumeApi, ConvertData convertData) {
        String url = BASE_URL + "?search=" + title.toLowerCase().replace(" ", "%20");
        String json = consumeApi.getData(url);

        List<Book> books = convertData.getBooksFromJson(json);
        books.forEach((book) -> {
            bookService.addBook(book.getTitle(), book.getAuthor());
            System.out.println(book.toString());
        });
    }

    // Lista todos os livros
    private void listAllBooks() {
        bookRepository.findAll().forEach(System.out::println);
    }

    // Lista todos os autores
    private void listAuthors() {
        authorRepository.findAll().forEach(System.out::println);
    }

    // Lista os autores vivos em determinado ano
    private void listLivingAuthorsByYear(int year) {
        List<Author> authors = authorRepository.findAll();
        authors.stream()
                .filter(author -> {
                    try {
                        int birthYear = Integer.parseInt(author.getBirthDate().split("-")[0]);
                        int deathYear = author.getDeathDate().isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(author.getDeathDate().split("-")[0]);

                        return year >= birthYear && year <= deathYear;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .forEach(System.out::println);
    }

    // Listar autores por idioma
    private void listAuthorsByLanguage(String language) {
        List<Book> books = bookRepository.findAll();
        books.stream()
                .filter(book -> book.getLanguage().equalsIgnoreCase(language))
                .map(Book::getAuthor)
                .distinct()
                .forEach(System.out::println);
    }
}
