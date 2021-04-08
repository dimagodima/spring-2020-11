package ru.otus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.model.Book;
import ru.otus.service.BookService;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/","/main", ""})
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/books")
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/get_book_form")
    public String getBookForm(Model model){
        return "get_book_form";
    }

    @GetMapping("/get_book")
    public String getBook(@RequestParam("id") String id, Model model){
        Book book = bookService.getBook(Long.parseLong(id));
        model.addAttribute("book", book);
        return "get_book";
    }

    @GetMapping("/update_book_form")
    public String getUpdateBookForm(Model model){
        return "update_book_form";
    }

    @GetMapping("/update_book")
    public String updateBook(@RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("genre_id") String genreId,
                             @RequestParam("author_id") String authorId, Model model){
        bookService.updateBook(Long.parseLong(id),name,Long.parseLong(genreId),Long.parseLong(authorId));
        model.addAttribute("success", "Book is updated.");
        return "success";
    }

    @GetMapping("/delete_book_form")
    public String getDeleteBookForm(Model model){
        return "delete_book_form";
    }

    @GetMapping("/delete_book")
    public String deleteBook(@RequestParam("id") long id,
                             Model model){
        bookService.deleteBook(id);
        model.addAttribute("success", "Book with id = " + id + " is deleted.");
        return "success";
    }

    @GetMapping("/add_book_form")
    public String getAddBookForm(Model model){
        return "add_book_form";
    }

    @GetMapping("/add_book")
    public String addBook(@RequestParam("name")String name,
                          @RequestParam("genre_id") long genreId,
                          @RequestParam("author_id") long authorId,
                          Model model){
        bookService.addBook(name,genreId,authorId);
        model.addAttribute("success", "Book with name = " + name + " is added to Libarary");
        return "success";
    }
}
