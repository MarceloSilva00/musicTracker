package com.marcelo.tracks.services.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Artist {

    private Long id;

    private String name;

    private List<TrackResponseBody> tracks;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(Long id, String name, List<TrackResponseBody> tracks) {
        this.id = id;
        this.name = name;
        this.tracks = tracks;
    }

    public Artist(Long id, String name) {
        this.id = id;
        this.name = name;
        this.tracks = null;
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

    public List<TrackResponseBody> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackResponseBody> tracks) {
        this.tracks = tracks;
    }


}
