package ru.otus.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.repository.BookRepository;

@ChangeLog
public class MongockChangeLog {

    @ChangeSet(order = "001", id = "dropDb", runAlways = true, author = "me")
    public void dropDb(MongoDatabase db){
        db.drop();
    }
    @ChangeSet(order = "002", id = "insertFirstBook", author = "me")
    public void insertFirstBook(BookRepository repository){
        repository.save(new Book("Одиссея",new Author("Гомер"),new Genre("Эпос")));
    }
    @ChangeSet(order = "003", id = "insertSecondBook", author = "me")
    public void insertSecondBook(BookRepository repository){
        repository.save(new Book("Хеллсинг",new Author("Хирано Кота"), new Genre("Манга")));
    }
    @ChangeSet(order = "003", id = "insertThirdBook", author = "me")
    public void insertThirdBook(BookRepository repository){
        repository.save(new Book("Сияние",new Author("Стивен Кинг"), new Genre("Ужасы")));
    }
}
