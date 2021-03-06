package otus.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
@NamedEntityGraph(name = "books-entity-graph",
            subgraphs = {
        @NamedSubgraph(name = "genre-subgraph",
                attributeNodes = @NamedAttributeNode(value = "genre", subgraph = "genre-subgraph")),
        @NamedSubgraph(name = "author-subgraph",
                attributeNodes = @NamedAttributeNode(value = "author", subgraph = "author-subgraph"))
            })

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    public Book() {
    }

    public Book(Long id, String name, Genre genre, Author author, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", author=" + author +
                ", comments=" + comments +
                '}';
    }
}
