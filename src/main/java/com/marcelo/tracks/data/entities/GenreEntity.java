package com.marcelo.tracks.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    private String genre;

    @OneToMany(mappedBy = "genre", cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    private List<TrackEntity> tracks;

    public GenreEntity(String genre) {
        this.genre = genre;
    }

    private GenreEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<TrackEntity> getTracks() {
        return tracks;
    }

}
