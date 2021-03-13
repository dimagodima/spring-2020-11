package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class ShellController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final GenreRepository genreRepository;

    public ShellController(AuthorRepository authorRepository, BookRepository bookRepository, CommentRepository commentRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.genreRepository = genreRepository;
    }


    @ShellMethod(value = "Welcome to library.", key = {"start"})
    public String welcomeCommand(){
        return "Welcome to our library.";
    }

    @ShellMethod(value = "Add book", key = {"save_book"})
    public String addBookCommand(@ShellOption() Book book){

        bookRepository.save(book);

        return "Book added to library.";
    }

    @ShellMethod(value = "Add author", key = {"save_author"})
    public String addAuthorCommand(@ShellOption() Author author){

        authorRepository.save(author);

        return "Author added to library.";
    }

    @ShellMethod(value = "Add genre", key = {"save_genre"})
    public String addGenreCommand(@ShellOption() Genre genre){

        genreRepository.save(genre);

        return "Genre added to library.";
    }

    @ShellMethod(value = "Add comment", key = {"save_comment"})
    public String addGenreCommand(@ShellOption()Comment comment){

        commentRepository.save(comment);

        return "Comment added for book.";
    }

    @ShellMethod(value = "Update book name by id", key = {"update_book"})
    public String updateBookNameByIdCommand(@ShellOption() Long id,
                                            @ShellOption() String name){
        bookRepository.updateBookNameById(id,name);
        return "Book with id " + id + " successful updated.";
    }

    @ShellMethod(value = "Delete book by id", key = {"delete_book"})
    public String deleteBookByIdCommand(@ShellOption() Long id){
        bookRepository.deleteById(id);
        return "Book with id " + id + " successful deleted.";
    }

    @ShellMethod(value = "Get book by name", key = {"find_book_by_name"})
    public String getBookByNameCommand(@ShellOption() String bookName){
        List<Book> books = bookRepository.findByName(bookName);

        return books.toString();
    }

    @ShellMethod(value = "Get book by id", key = {"find_book_by_id"})
    public String findBookByIdCommand(@ShellOption() Long bookId){
        Optional<Book> books = bookRepository.findById(bookId);

        return books.toString();
    }


}
