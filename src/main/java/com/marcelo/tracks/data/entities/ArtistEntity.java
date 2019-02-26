package com.marcelo.tracks.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    private String name;


    @ManyToMany(mappedBy = "artists" , cascade = {CascadeType.DETACH})
    private List<TrackEntity> tracks = new ArrayList<>();

    public ArtistEntity() {
    }

    public ArtistEntity(String name) {
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

    public List<TrackEntity> getTrackEntities() {
        return tracks;
    }

    public TrackEntity addTrack(TrackEntity trackEntity) {
        this.tracks.add(trackEntity);
        return trackEntity;
    }

    public void setTracks(List<TrackEntity> tracks) {
        this.tracks = tracks;
    }
}
