package com.bladi._k.liter_alura_challenge.main;

import com.bladi._k.liter_alura_challenge.models.*;
import com.bladi._k.liter_alura_challenge.repository.AuthorRepository;
import com.bladi._k.liter_alura_challenge.repository.BookRepository;
import com.bladi._k.liter_alura_challenge.services.DataConverter;
import com.bladi._k.liter_alura_challenge.services.GutendexClient;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainApplication {

    private final Scanner scanner = new Scanner(System.in);
    private final GutendexClient client = new GutendexClient();
    private final DataConverter converter = new DataConverter();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public MainApplication(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void searchBookByTitle() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var bookName = scanner.nextLine();
        var json = client.getBookData(bookName);
        ResultsData results = converter.getData(json, ResultsData.class);

        if (results != null && results.results() != null && !results.results().isEmpty()) {
            BookData bookData = results.results().get(0);
            
            Optional<Book> existingBook = bookRepository.findByTitleContainsIgnoreCase(bookData.title());
            if(existingBook.isPresent()){
                System.out.println("El libro ya está registrado.");
                return;
            }

            AuthorData authorData = bookData.authors().get(0);
            Author author = authorRepository.findByName(authorData.name()).orElseGet(() -> new Author(authorData));
            
            authorRepository.save(author);

            Book book = new Book(bookData);
            book.setAuthor(author);
            bookRepository.save(book);

            System.out.println("Libro guardado exitosamente:");
            System.out.println(book);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listRegisteredBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void listRegisteredAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private void listAuthorsByYear() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        var year = scanner.nextInt();
        scanner.nextLine();
        List<Author> authors = authorRepository.findAuthorsByYear(year);
        if (authors.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private void listBooksByLanguage() {
        System.out.println("Ingrese el idioma para buscar los libros (es, en, fr, pt):");
        var lang = scanner.nextLine();
        List<Book> books = bookRepository.findByLanguage(lang);
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
        } else {
            books.forEach(System.out::println);
        }
    }
}
