package com.marcelo.tracks.services;


import com.marcelo.tracks.services.model.Artist;

public interface ArtistService {

    Artist findByName(String name);

    Iterable<Artist> findAll();

    Artist add(Artist artist);

    Artist findById(Long id);

    Iterable<Artist> findAllByNameContains(String name);

    Artist edit(Long id, Artist artist);
}
