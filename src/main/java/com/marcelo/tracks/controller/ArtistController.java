package com.marcelo.tracks.controller;

import com.marcelo.tracks.services.ArtistService;
import com.marcelo.tracks.services.model.Artist;
import com.marcelo.tracks.services.model.TrackResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public Iterable<Artist> findAllArtists(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return artistService.findAllByNameContains(name);
        }
        return this.artistService.findAll();
    }

    @GetMapping("/{artist_id}")
    public Artist findArtistByName(@PathVariable(value = "artist_id") Long artistId) {
        return artistService.findById(artistId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.add(artist);
    }

    @GetMapping("/{artist_id}/tracks")
    public Iterable<TrackResponseBody> findAllTracksOfArtist(@PathVariable(value = "artist_id") Long artistId) {
        Artist artist = artistService.findById(artistId);
        return artist.getTracks();
    }

    @PutMapping("/{artist_id}")
    public Artist edit(@PathVariable(value = "artist_id") Long id, @RequestBody Artist artist){
        return artistService.edit(id,artist);
    }
}
