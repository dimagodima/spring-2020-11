package ru.otus.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.model.Book;
import ru.otus.repository.BookRepository;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/get/{id}")
    public Mono<Book> getBook(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/books/save")
    public Mono<Book> saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/books/delete/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bookRepository.deleteById(id);
    }
}
