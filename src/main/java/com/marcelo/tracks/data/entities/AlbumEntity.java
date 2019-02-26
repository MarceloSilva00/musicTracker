package com.marcelo.tracks.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    private String name;

    private int year;

    @ManyToMany(mappedBy = "albums")
    private List<TrackEntity> tracks = new ArrayList<>();

    public AlbumEntity(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public AlbumEntity() {
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

    public List<TrackEntity> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackEntity> tracks) {
        this.tracks = tracks;
    }
}
