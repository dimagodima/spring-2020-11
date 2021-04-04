package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Author;
import ru.otus.repository.AuthorRepository;
import ru.otus.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void deleteAuthorById(String id) {
        repository.delete(new Author(id));
    }

    @Override
    @Transactional
    public void addAuthor(Author author) {
        repository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(String id) {
        return repository.findById(id).get();
    }
}
