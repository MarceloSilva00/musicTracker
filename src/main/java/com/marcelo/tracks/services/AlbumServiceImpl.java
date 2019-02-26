package com.marcelo.tracks.services;

import com.marcelo.tracks.data.entities.AlbumEntity;
import com.marcelo.tracks.data.entities.GenreEntity;
import com.marcelo.tracks.data.entities.TrackEntity;
import com.marcelo.tracks.data.repository.AlbumRepository;
import com.marcelo.tracks.exception.ResourceNotFound;
import com.marcelo.tracks.services.model.Album;
import com.marcelo.tracks.services.model.TrackResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }


    @Override
    public Iterable<Album> findAll() {
        return serializeToModelIterable(albumRepository.findAll());
    }

    @Override
    public Album findById(Long id) {
        return serializeToModel(findByIdEntity(id));
    }

    @Override
    public Album add(Album album) {
        return serializeToModel(
                albumRepository.save(
                        serializeToEntity(album)
                )
        );
    }

    @Override
    public Iterable<Album> findAllSortedByYear() {
        return serializeToModelIterable(albumRepository.findAllByOrderByYearDesc());
    }

    @Override
    public Iterable<Album> findByNameContaining(String name) {
        return serializeToModelIterable(albumRepository.findByNameContaining(name));
    }

    @Override
    public Iterable<Album> findAllSortedByGenre() {
        List<AlbumEntity> albumEntities = new LinkedList<>();
        albumRepository.findAll().forEach(albumEntities::add);
        Map<AlbumEntity, Integer> albumMap = new HashMap<>();
        for (AlbumEntity album : albumEntities) {
            Integer id = getGenreIdOfAlbum(album);
            albumMap.put(album, id);
        }
        albumEntities = sortByGenreId(albumMap);

        return serializeToModelIterable(albumEntities);
    }

    @Override
    public Album edit(Long id, Album albumEdited) {
        AlbumEntity album = findByIdEntity(id);

        album.setName(albumEdited.getName());
        album.setYear(albumEdited.getYear());

        return serializeToModel(
                albumRepository.save(album)
        );

    }




    // UTILS

    private AlbumEntity findByIdEntity(Long id){
        Optional<AlbumEntity> albumEntity = albumRepository.findById(id);

        if (albumEntity.isPresent()) {
            return albumEntity.get();
        }
        throw new ResourceNotFound("Album not Found");
    }

    private List<AlbumEntity> sortByGenreId(Map<AlbumEntity, Integer> albumMap) {
        Map<AlbumEntity, Integer> sortedNewMap = albumMap.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        List<AlbumEntity> albums = new LinkedList<>(sortedNewMap.keySet());
        return albums;
    }


    private Integer getGenreIdOfAlbum(AlbumEntity albumEntity) {
        Map<GenreEntity, Integer> genreMap = new HashMap<>();
        for (TrackEntity trackEntity : albumEntity.getTracks()) {
            if (genreMap.containsKey(trackEntity.getGenre())) {
                genreMap.put(trackEntity.getGenre(), genreMap.get(trackEntity.getGenre()) + 1);
            } else {
                genreMap.put(trackEntity.getGenre(), 1);
            }
        }

        //Find the most common genre on the album
        int maxEntry = 0;
        GenreEntity maxEntity = null;
        for (Map.Entry<GenreEntity, Integer> entry : genreMap.entrySet()) {
            if (entry.getValue() > maxEntry) {
                maxEntry = entry.getValue();
                maxEntity = entry.getKey();
            }
        }

        return maxEntity.getId().intValue();
    }


    private Album serializeToModel(AlbumEntity albumEntity) {
        List<TrackResponseBody> tracks = new LinkedList<>();
        for (TrackEntity track : albumEntity.getTracks()) {
            tracks.add(new TrackResponseBody(track.getId(), track.getName()));
        }
        return new Album(albumEntity.getId(), albumEntity.getName(), albumEntity.getYear(), tracks);
    }

    private Iterable<Album> serializeToModelIterable(Iterable<AlbumEntity> albumEntities) {
        List<Album> albums = new LinkedList<>();

        for (AlbumEntity albumEntity : albumEntities) {
            albums.add(serializeToModel(albumEntity));
        }
        return albums;
    }


    private AlbumEntity serializeToEntity(Album album) {
        return new AlbumEntity(album.getName(), album.getYear());
    }

}