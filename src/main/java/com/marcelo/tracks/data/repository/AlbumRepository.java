package com.marcelo.tracks.data.repository;

import com.marcelo.tracks.data.entities.AlbumEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends CrudRepository<AlbumEntity,Long> {

    Iterable<AlbumEntity> findAllByOrderByYearDesc();

    Iterable<AlbumEntity> findByNameContaining(String name);

}
