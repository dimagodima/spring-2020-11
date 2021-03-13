package ru.otus.domain;

import javax.persistence.*;

@Entity
@Table(name = "books")
@NamedEntityGraph(name = "books-entity-graph",
            subgraphs = {
        @NamedSubgraph(name = "genre-subgraph",
                attributeNodes = @NamedAttributeNode(value = "genre", subgraph = "genre-subgraph")),
        @NamedSubgraph(name = "author-subgraph",
                attributeNodes = @NamedAttributeNode(value = "author", subgraph = "author-subgraph")),
        @NamedSubgraph(name = "comment-subgraph",
                attributeNodes = @NamedAttributeNode(value = "comment", subgraph = "comment-subgraph"))
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

    @ManyToOne(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Book() {
    }

    public Book(Long id, String name, Genre genre, Author author, Comment comment) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.comment = comment;
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", author=" + author +
                ", comment=" + comment +
                '}';
    }
}
