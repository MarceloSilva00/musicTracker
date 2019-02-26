package com.marcelo.tracks.services;

import com.marcelo.tracks.services.model.Track;
import com.marcelo.tracks.services.model.TrackRequestBody;

public interface TrackService {

    Iterable<Track> findAll();

    Track add(TrackRequestBody track);

    Track findById(Long id);

    Iterable<Track> findAllByNameContains(String name);

    Track edit(Long id, TrackRequestBody track);
}
