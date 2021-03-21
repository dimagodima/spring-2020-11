package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Author;
import ru.otus.model.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.GenreRepository;
import ru.otus.service.BookService;
import ru.otus.model.Book;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository repository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void deleteBookById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        repository.save(book);
    }

    @Override
    @Transactional
    public void updateBookById(String bookId, String name, String genreId, String authorId) {
        Author author = authorRepository.findById(authorId).get();
        Genre genre = genreRepository.findById(genreId).get();
        if(repository.findById(bookId).isPresent()){
            repository.save(new Book(bookId, name, author, genre));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(String id) {
        return repository.findById(id).get();
    }
}
