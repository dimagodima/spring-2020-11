//package otus.shell;
//
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//import org.springframework.transaction.annotation.Transactional;
//import otus.domain.Author;
//import otus.domain.Book;
//import otus.domain.Comment;
//import otus.domain.Genre;
//import otus.repository.AuthorRepository;
//import otus.repository.BookRepository;
//import otus.repository.CommentRepository;
//import otus.repository.GenreRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@ShellComponent
//public class ShellController {
//
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//    private final CommentRepository commentRepository;
//    private final GenreRepository genreRepository;
//
//    public ShellController(AuthorRepository authorRepository, BookRepository bookRepository, CommentRepository commentRepository, GenreRepository genreRepository) {
//        this.authorRepository = authorRepository;
//        this.bookRepository = bookRepository;
//        this.commentRepository = commentRepository;
//        this.genreRepository = genreRepository;
//    }
//
//
//    @ShellMethod(value = "Welcome to library.", key = {"start"})
//    public String welcomeCommand(){
//        return "Welcome to our library.";
//    }
//
//    @ShellMethod(value = "Add book", key = {"save_book"})
//    public String addBookCommand(@ShellOption() Book book){
//
//        bookRepository.saveBook(book);
//
//        return "Book added to library.";
//    }
//
//    @ShellMethod(value = "Add author", key = {"save_author"})
//    public String addAuthorCommand(@ShellOption() Author author){
//
//        authorRepository.saveAuthor(author);
//
//        return "Author added to library.";
//    }
//
//    @ShellMethod(value = "Add genre", key = {"save_genre"})
//    public String addGenreCommand(@ShellOption() Genre genre){
//
//        genreRepository.saveGenre(genre);
//
//        return "Genre added to library.";
//    }
//
//    @ShellMethod(value = "Add comment", key = {"save_comment"})
//    public String addGenreCommand(@ShellOption()Comment comment){
//
//        commentRepository.saveComment(comment);
//
//        return "Comment added for book.";
//    }
//
//    @Transactional
//    @ShellMethod(value = "Update book name by id", key = {"update_book"})
//    public String updateBookNameByIdCommand(@ShellOption() Long id,
//                                            @ShellOption() String name){
//        Optional<Book> bookById = bookRepository.findBookById(id);
//        bookById.get().setName(name);
//        bookRepository.updateBook(bookById.get());
//        return "Book with id " + id + " successful updated.";
//    }
//
//    @ShellMethod(value = "Delete book by id", key = {"delete_book"})
//    public String deleteBookByIdCommand(@ShellOption() Long id){
//        bookRepository.deleteBookById(new Book(id,null,null,null,null));
//        return "Book with id " + id + " successful deleted.";
//    }
//
//    @ShellMethod(value = "Get book by name", key = {"find_book_by_name"})
//    public String getBookByNameCommand(@ShellOption() String bookName){
//        List<Book> books = bookRepository.findBookByName(bookName);
//
//        return books.toString();
//    }
//
//    @ShellMethod(value = "Get book by id", key = {"find_book_by_id"})
//    public String getBookByIdCommand(@ShellOption() Long bookId){
//        Optional<Book> books = bookRepository.findBookById(bookId);
//
//        return books.toString();
//    }
//
//    @ShellMethod(value = "Get all comments for book by book id", key = {"find_all_comments_for_book"})
//    public void getAllCommentForBook(@ShellOption() Long bookId){
//        Optional<Book> books = bookRepository.findBookById(bookId);
//
//        books.stream().forEach(System.out::println);
//    }
//
//    @ShellMethod(value = "Delete comment by id", key = {"del_com"})
//    public String deleteCommentById(@ShellOption() Long id){
//        commentRepository.deleteCommentById(new Comment(id,null));
//
//        return "comment deleted";
//    }
//
//    @ShellMethod(value = "Delete comment by id", key = {"find_all_books"})
//    public void findAllBooks(){
//        List<Book> allBooks = bookRepository.findAllBooks();
//
//        allBooks.forEach(System.out::println);
//    }
//}
