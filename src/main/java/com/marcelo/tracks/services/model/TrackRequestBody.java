package com.marcelo.tracks.services.model;

import java.util.List;

public class TrackRequestBody {

    private String name;

    private Double duration;

    private List<Long> artistsIds;

    private Long genreId;

    private List<Long> albumsIds;

    public TrackRequestBody(String name, Double duration, Long genreId, List<Long> artistIds, List<Long> albumsIds) {
        this.artistsIds = artistIds;
        this.name = name;
        this.duration = duration;
        this.genreId = genreId;
        this.albumsIds = albumsIds;
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

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public List<Long> getArtistsIds() {
        return artistsIds;
    }

    public void setArtistsIds(List<Long> artistsIds) {
        this.artistsIds = artistsIds;
    }

    public List<Long> getAlbumsIds() {
        return albumsIds;
    }

    public void setAlbumsIds(List<Long> albumsIds) {
        this.albumsIds = albumsIds;
    }
}
