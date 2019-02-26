package com.marcelo.tracks.data.repository;

import com.marcelo.tracks.data.entities.ArtistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<ArtistEntity, Long> {

    ArtistEntity findByName(String name);

    Iterable<ArtistEntity> findAllByNameContains(String name);
}
