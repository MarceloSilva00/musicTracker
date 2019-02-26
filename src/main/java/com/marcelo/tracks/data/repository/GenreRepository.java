package com.marcelo.tracks.data.repository;

import com.marcelo.tracks.data.entities.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<GenreEntity,Long> {
}
