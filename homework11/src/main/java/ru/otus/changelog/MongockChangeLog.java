package ru.otus.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.repository.BookRepository;

@ChangeLog(order = "001")
public class MongockChangeLog {

    @ChangeSet(order = "000", id = "dropDb", runAlways = true, author = "me")
    public void dropDb(MongoDatabase db){
        db.drop();
    }
    @ChangeSet(order = "001", id = "insertFirstBook", author = "me", runAlways = true)
    public void insertFirstBook(BookRepository repository){
        repository.save(new Book("1","Одиссея",new Author("Гомер"),new Genre("Эпос")));
    }
    @ChangeSet(order = "002", id = "insertSecondBook", author = "me", runAlways = true)
    public void insertSecondBook(BookRepository repository){
        repository.save(new Book("Хеллсинг",new Author("Хирано Кота"), new Genre("Манга")));
    }
    @ChangeSet(order = "003", id = "insertThirdBook", author = "me", runAlways = true)
    public void insertThirdBook(BookRepository repository){
        repository.save(new Book("Сияние",new Author("Стивен Кинг"), new Genre("Ужасы")));
    }
}
