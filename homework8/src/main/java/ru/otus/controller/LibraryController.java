package ru.otus.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.model.Book;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.Optional;

@Controller
public class LibraryController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    private final GenreRepository genreRepository;

    public LibraryController(BookRepository bookRepository, AuthorRepository authorRepository, CommentRepository commentRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/get_book")
    public Optional<Book> getBook(@Param("id") String id){
       return bookRepository.findById(id);
    }

    @PostMapping
    public void addBook(@Param())
}
