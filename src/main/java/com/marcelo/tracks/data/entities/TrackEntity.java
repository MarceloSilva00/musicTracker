package com.marcelo.tracks.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tracks")
public class TrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Long id;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Artist_Track",
            joinColumns = {@JoinColumn(name = "track_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")}
    )
    private List<ArtistEntity> artists = new ArrayList<>();

    private String name;

    private Double duration;

    @ManyToOne
    @JoinColumn(name = "genreid")
    private GenreEntity genre;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Track_Album",
            joinColumns = {@JoinColumn(name = "track_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")}
    )
    private List<AlbumEntity> albums = new ArrayList<>();

    public TrackEntity(String name, Double duration, GenreEntity genreEntity) {
        this.name = name;
        this.duration = duration;
        this.genre = genreEntity;
    }

    public TrackEntity(String name, Double duration, GenreEntity genreEntity, List<ArtistEntity> artistEntities , List<AlbumEntity> albumEntities) {
        this.name = name;
        this.duration = duration;
        this.genre = genreEntity;
        this.artists = artistEntities;
        this.albums = albumEntities;
    }

    public TrackEntity() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public List<ArtistEntity> getArtistEntities() {
        return artists;
    }

    public List<ArtistEntity> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistEntity> artists) {
        this.artists = artists;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public List<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumEntity> albums) {
        this.albums = albums;
    }
}
