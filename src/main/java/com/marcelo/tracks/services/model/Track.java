package com.marcelo.tracks.services.model;

import java.util.ArrayList;
import java.util.List;

public class Track {

    private Long id;

    private List<Artist> artists = new ArrayList<>();

    private String name;

    private Double duration;

    private String genre;

    private List<Album> albums = new ArrayList<>();

    public Track(Long id, List<Artist> artists, String name, Double duration, String genre, List<Album> albums) {
        this.id = id;
        this.artists = artists;
        this.name = name;
        this.duration = duration;
        this.genre = genre;
        this.albums = albums;
    }


    public Track() {
    }

    public Track(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
