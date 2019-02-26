package com.marcelo.tracks.services;

import com.marcelo.tracks.services.model.Genre;

public interface GenreService {

    Iterable<Genre> listAll();

    Genre add(Genre genre);

    Genre findById(Long id);

    void delete(long id);

    Genre edit(long id, Genre genre);
}
