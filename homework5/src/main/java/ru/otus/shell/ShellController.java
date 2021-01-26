package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.dao.LibraryDao;
import ru.otus.domain.Book;

@ShellComponent
public class ShellController {

    private final LibraryDao libraryDao;

    public ShellController(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    @ShellMethod(value = "Welcome to library.", key = {"start"})
    public String welcomeCommand(){
        return "Welcome to our library.";
    }

    @ShellMethod(value = "Add book", key = {"add_book"})
    public String addBookCommand(@ShellOption() Book book){

        libraryDao.addBook(book);

        return "Book added to library.";
    }

    @ShellMethod(value = "Add author", key = {"add_author"})
    public String addAuthorCommand(@ShellOption() String author){

        libraryDao.addAuthor(author);

        return "Author added to library.";
    }

    @ShellMethod(value = "Add genre", key = {"add_genre"})
    public String addGenreCommand(@ShellOption() String genre){

        libraryDao.addGenre(genre);

        return "Genre added to library.";
    }

    @ShellMethod(value = "Update book by id", key = {"update_book"})
    public String updateBookByIdCommand(@ShellOption() Long id,
                                        @ShellOption() String name,
                                        @ShellOption() Long authorId,
                                        @ShellOption() Long genreId){
        libraryDao.updateBookById(id,name,authorId,genreId);
        return "Book with id " + id + " successful updated.";
    }

    @ShellMethod(value = "Delete book by id", key = {"delete_book"})
    public String deleteBookByIdCommand(@ShellOption() Long id){
        libraryDao.deleteBookById(id);
        return "Book with id " + id + " successful deleted.";
    }

    @ShellMethod(value = "Get book by name", key = {"get_book_by_name"})
    public String getBookByNameCommand(@ShellOption() String bookName){
        Book book = libraryDao.getBookByName(bookName);
        book.setAuthor(libraryDao.findAuthorById(book.getAuthorId()));
        book.setGenre(libraryDao.findGenreById(book.getGenreId()));

        return book.toString();
    }

    @ShellMethod(value = "Get book by id", key = {"get_book_by_id"})
    public String getBookByIdCommand(@ShellOption() Long bookId){
        Book book = libraryDao.getBookById(bookId);
        book.setAuthor(libraryDao.findAuthorById(book.getAuthorId()));
        book.setGenre(libraryDao.findGenreById(book.getGenreId()));

        return book.toString();
    }


}
