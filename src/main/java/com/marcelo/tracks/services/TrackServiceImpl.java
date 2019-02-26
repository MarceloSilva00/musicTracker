package com.marcelo.tracks.services;

import com.marcelo.tracks.data.entities.AlbumEntity;
import com.marcelo.tracks.data.entities.ArtistEntity;
import com.marcelo.tracks.data.entities.GenreEntity;
import com.marcelo.tracks.data.entities.TrackEntity;
import com.marcelo.tracks.data.repository.AlbumRepository;
import com.marcelo.tracks.data.repository.ArtistRepository;
import com.marcelo.tracks.data.repository.GenreRepository;
import com.marcelo.tracks.data.repository.TrackRepository;
import com.marcelo.tracks.exception.ResourceNotFound;
import com.marcelo.tracks.services.model.Album;
import com.marcelo.tracks.services.model.Artist;
import com.marcelo.tracks.services.model.Track;
import com.marcelo.tracks.services.model.TrackRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;


    public TrackServiceImpl(TrackRepository trackRepository, GenreRepository genreRepository, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.trackRepository = trackRepository;
        this.genreRepository = genreRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public Iterable<Track> findAll() {
        return serializeToModelIterable(trackRepository.findAll());
    }

    @Override
    public Track add(TrackRequestBody track) {
        return serializeToModel(trackRepository.save(
                serializeRequestBodyToEntity(track)
        ));
    }

    @Override
    public Track findById(Long id) {
        return serializeToModel(
                findByIdEntity(id)
        );
    }

    @Override
    public Iterable<Track> findAllByNameContains(String name) {
        return serializeToModelIterable(trackRepository.findAllByNameContains(name));
    }

    @Override
    public Track edit(Long id, TrackRequestBody trackEdit) {
        TrackEntity track = findByIdEntity(id);

        TrackEntity trackEditEntity = serializeRequestBodyToEntity(trackEdit);

        track.setName(trackEditEntity.getName());
        track.setDuration(trackEditEntity.getDuration());
        track.setAlbums(trackEditEntity.getAlbums());
        track.setArtists(trackEditEntity.getArtists());
        track.setGenre(trackEditEntity.getGenre());

        return serializeToModel(trackRepository.save(track));
    }


    //UTILS


    private TrackEntity findByIdEntity(Long id) {
        Optional<TrackEntity> trackEntity = trackRepository.findById(id);
        if (trackEntity.isPresent()) {
            return trackEntity.get();
        }
        throw new ResourceNotFound("Track not Found");
    }

    private TrackEntity serializeRequestBodyToEntity(TrackRequestBody track) {
        Optional<GenreEntity> genreEntity = genreRepository.findById(track.getGenreId());
        if (!genreEntity.isPresent()) {
            throw new ResourceNotFound("Genre not Found");
        }


        List<AlbumEntity> albumEntities = new LinkedList<>();
        for (Long id : track.getAlbumsIds()) {
            Optional<AlbumEntity> albumEntity = albumRepository.findById(id);
            if (albumEntity.isPresent()) {
                albumEntities.add(albumEntity.get());
                continue;
            }
            throw new ResourceNotFound("Album with id: " + id + " not Found");
        }


        List<ArtistEntity> artistEntities = new LinkedList<>();
        for (Long id : track.getArtistsIds()) {
            Optional<ArtistEntity> artistEntity = artistRepository.findById(id);
            if (artistEntity.isPresent()) {
                artistEntities.add(artistEntity.get());
                continue;
            }
            throw new ResourceNotFound("Artist with id: " + id + " not Found");
        }
        return new TrackEntity(track.getName(), track.getDuration(), genreEntity.get(), artistEntities, albumEntities);


    }

    private Track serializeToModel(TrackEntity trackEntity) {
        List<ArtistEntity> artistEntities = trackEntity.getArtistEntities();
        List<Artist> artists = new LinkedList<>();

        for (ArtistEntity artist : artistEntities) {
            artists.add(new Artist(artist.getId(), artist.getName()));
        }

        List<AlbumEntity> albumEntities = trackEntity.getAlbums();
        List<Album> albums = new LinkedList<>();

        for (AlbumEntity album : albumEntities) {
            albums.add(new Album(album.getId(), album.getName(), album.getYear(), null));
        }

        if (trackEntity.getGenre() != null) {
            if (trackEntity.getAlbums() != null) {
                return new Track(trackEntity.getId(), artists, trackEntity.getName(), trackEntity.getDuration(), trackEntity.getGenre().getGenre(), albums);
            } else {
                return new Track(trackEntity.getId(), artists, trackEntity.getName(), trackEntity.getDuration(), trackEntity.getGenre().getGenre(), null);
            }
        } else {
            return new Track(trackEntity.getId(), artists, trackEntity.getName(), trackEntity.getDuration(), null, null);
        }
    }

    private Iterable<Track> serializeToModelIterable(Iterable<TrackEntity> trackEntities) {
        List<Track> tracks = new LinkedList<>();
        for (TrackEntity trackEntity : trackEntities) {
            tracks.add(serializeToModel(trackEntity));
        }
        return tracks;
    }
}
