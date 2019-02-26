package com.marcelo.tracks.controller;

import com.marcelo.tracks.services.TrackService;
import com.marcelo.tracks.services.model.Track;
import com.marcelo.tracks.services.model.TrackRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public Iterable<Track> findAll(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return trackService.findAllByNameContains(name);
        }
        return trackService.findAll();
    }

    @PostMapping
    public Track add(@RequestBody TrackRequestBody track) {
        return trackService.add(track);
    }

    @GetMapping("/{track_id}")
    public Track findById(@PathVariable(value = "track_id") Long id) {
        return trackService.findById(id);
    }

    @PutMapping("/{track_id}")
    public Track edit(@PathVariable(value = "track_id") Long id, @RequestBody TrackRequestBody track){
        return trackService.edit(id,track);
    }

}
