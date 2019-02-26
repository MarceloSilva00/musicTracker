package com.marcelo.tracks.data.repository;

import com.marcelo.tracks.data.entities.TrackEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends CrudRepository<TrackEntity, Long> {


    Iterable<TrackEntity> findAllByNameContains(String name);
}
