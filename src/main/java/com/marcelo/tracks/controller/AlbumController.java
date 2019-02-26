package com.marcelo.tracks.controller;

import com.marcelo.tracks.services.AlbumService;
import com.marcelo.tracks.services.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping
    public Iterable<Album> listAll(@RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "name", required = false) String name) {
        if (sort != null) {
            if (sort.equalsIgnoreCase("genre")) {
                return albumService.findAllSortedByGenre();
            } else if (sort.equalsIgnoreCase("year")) {
                return albumService.findAllSortedByYear();
            }
        }
        if (name != null) {
            return albumService.findByNameContaining(name);
        }
        return albumService.findAll();
    }


    @GetMapping("/{album_id}")
    public Album findById(@PathVariable(name = "album_id") Long id) {
        return albumService.findById(id);

    }

    @PostMapping
    public Album add(@RequestBody Album album) {
        return albumService.add(album);
    }

    @PutMapping("/{album_id}")
    public Album edit(@PathVariable(name = "album_id") Long id,@RequestBody Album album){
        return albumService.edit(id,album);
    }


}
