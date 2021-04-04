package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Genre;
import ru.otus.repository.GenreRepository;
import ru.otus.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void deleteGenreById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void addGenre(Genre genre) {
        repository.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getGenreById(String id) {
       return repository.findById(id).get();
    }
}
