package com.marcelo.tracks.services;

import com.marcelo.tracks.data.entities.GenreEntity;
import com.marcelo.tracks.data.repository.GenreRepository;
import com.marcelo.tracks.exception.ResourceNotFound;
import com.marcelo.tracks.services.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Iterable<Genre> listAll() {
        return serializeToModelIterable(
                genreRepository.findAll()
        );
    }

    @Override
    public Genre add(Genre genre) {
        return serializeToModel(
                genreRepository.save(
                        serializeToEntity(genre)
                ));
    }

    @Override
    public Genre findById(Long id) {
        return serializeToModel(findByIdEntity(id));
    }

    @Override
    public void delete(long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Genre edit(long id, Genre genre) {
        GenreEntity genreEntity = findByIdEntity(id);
        genreEntity.setGenre(genre.getGenre());

        return serializeToModel(
                genreRepository.save(genreEntity)
        );
    }


    // UTILS

    private GenreEntity findByIdEntity(Long id) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(id);
        if (genreEntity.isPresent()) {
            return genreEntity.get();
        }
        throw new ResourceNotFound("Genre not Found");
    }


    private Genre serializeToModel(GenreEntity genreEntity) {
        return new Genre(genreEntity.getId(), genreEntity.getGenre());
    }

    private Iterable<Genre> serializeToModelIterable(Iterable<GenreEntity> genreEntities) {
        List<Genre> genres = new LinkedList<>();
        for (GenreEntity genreEntity : genreEntities) {
            genres.add(new Genre(genreEntity.getId(), genreEntity.getGenre()));
        }
        return genres;
    }

    private GenreEntity serializeToEntity(Genre genre) {
        return new GenreEntity(genre.getGenre());
    }

}
