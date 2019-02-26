package com.marcelo.tracks.services.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrackResponseBody {

    private Long id;

    private String name;

    private Double duration;

    private String genre;

    public TrackResponseBody(Long id, String name, Double duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public TrackResponseBody(Long id, String name) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
