package com.marcelo.tracks.services;

import com.marcelo.tracks.data.entities.ArtistEntity;
import com.marcelo.tracks.data.entities.TrackEntity;
import com.marcelo.tracks.data.repository.ArtistRepository;
import com.marcelo.tracks.exception.ResourceNotFound;
import com.marcelo.tracks.services.model.Artist;
import com.marcelo.tracks.services.model.TrackResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Override
    public Artist findByName(String name) {
        return serializeToModel(artistRepository.findByName(name));
    }

    @Override
    public Iterable<Artist> findAll() {
        return serializeToModelIterable(artistRepository.findAll());
    }

    @Override
    public Artist add(Artist artist) {
        return serializeToModel(
                artistRepository.save(
                        serializeToEntity(artist)
                ));
    }

    @Override
    public Artist findById(Long id) {
        return serializeToModel(
                findByIdEntity(id)
        );
    }

    @Override
    public Iterable<Artist> findAllByNameContains(String name) {
        return serializeToModelIterable(artistRepository.findAllByNameContains(name));
    }

    @Override
    public Artist edit(Long id, Artist artistEdit) {
        ArtistEntity artistEntity = findByIdEntity(id);

        ArtistEntity artistEntityEdit = serializeToEntity(artistEdit);

        artistEntity.setName(artistEntityEdit.getName());
        artistEntity.setTracks(artistEntityEdit.getTrackEntities());

        return serializeToModel(
                artistRepository.save(artistEntity)
        );
    }


    //UTILS


    private ArtistEntity findByIdEntity(Long id) {
        Optional<ArtistEntity> artistEntity = artistRepository.findById((long) id);
        if (artistEntity.isPresent()) {
            return artistEntity.get();
        }
        throw new ResourceNotFound("Artist not found");
    }

    private Artist serializeToModel(ArtistEntity artistEntity) {
        String name = artistEntity.getName();
        Long id = artistEntity.getId();
        List<TrackEntity> trackList = artistEntity.getTrackEntities();
        List<TrackResponseBody> trackIdList = new ArrayList<>(trackList.size());
        for (TrackEntity trackEntity : trackList) {
            trackIdList.add(new TrackResponseBody(trackEntity.getId(), trackEntity.getName()));
        }
        return new Artist(id, name, trackIdList);
    }

    private Iterable<Artist> serializeToModelIterable(Iterable<ArtistEntity> artistEntities) {
        List<Artist> artists = new LinkedList<>();
        for (ArtistEntity artistEntity : artistEntities) {
            artists.add(serializeToModel(artistEntity));
        }
        return artists;
    }

    private ArtistEntity serializeToEntity(Artist artist) {
        return new ArtistEntity(artist.getName());
    }
}
