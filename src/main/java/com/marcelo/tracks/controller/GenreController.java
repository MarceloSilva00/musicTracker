package com.marcelo.tracks.controller;

import com.marcelo.tracks.services.GenreService;
import com.marcelo.tracks.services.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public Iterable<Genre> listAll(){
        return genreService.listAll();
    }

    @GetMapping("/{genre_id}")
    public Genre findById(@PathVariable(name = "genre_id") long id){
        return genreService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre addGenre(@RequestBody Genre genre){
        return genreService.add(genre);
    }

    @DeleteMapping("/{genre_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "genre_id") long id){
        genreService.delete(id);
    }

    @PutMapping("/{genre_id}")
    public Genre edit(@PathVariable(value = "genre_id") long id,@RequestBody Genre genre){
        return genreService.edit(id,genre);
    }

}
