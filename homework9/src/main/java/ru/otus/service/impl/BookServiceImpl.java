package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;
import ru.otus.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public void addBook(String name, long genreId, long authorId) {
        Author author = authorRepository.findById(authorId).get();
        Genre genre = genreRepository.findById(genreId).get();
        bookRepository.save(new Book(name,genre,author));
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            commentRepository.deleteAllByBookId(id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book getBook(long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void updateBook(long bookId, String name, long genreId, long authorId) {
        Author author = authorRepository.findById(authorId).get();
        Genre genre = genreRepository.findById(genreId).get();
        Book book = bookRepository.findById(bookId).get();
        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
    }
}
