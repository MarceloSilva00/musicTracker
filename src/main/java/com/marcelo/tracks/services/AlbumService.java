package com.marcelo.tracks.services;

import com.marcelo.tracks.services.model.Album;

public interface AlbumService {

    Iterable<Album> findAll();

    Album findById(Long id);

    Album add(Album album);

    Iterable<Album> findAllSortedByYear();

    Iterable<Album> findByNameContaining(String name);

    Iterable<Album> findAllSortedByGenre();

    Album edit(Long id, Album album);
}
