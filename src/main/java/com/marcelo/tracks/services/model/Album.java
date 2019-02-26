package com.marcelo.tracks.services.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
public class Album {

    private Long id;

    private String name;

    private int year;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TrackResponseBody> tracks;

    public Album(Long id, String name, int year , List<TrackResponseBody> tracks) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.tracks = tracks;
    }

    public Album(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Album() {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<TrackResponseBody> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackResponseBody> tracks) {
        this.tracks = tracks;
    }
}
