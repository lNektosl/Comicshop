package com.daniil.comicshop.controler;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public List<Artist> getAllArtists(){
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Artist> getById(@PathVariable int id){
        return artistService.getById(id);
    }

    @PostMapping("/add")
    public Artist addArtist(@RequestBody Artist artist){
        return artistService.add(artist);
    }

    @PutMapping("/change")
    public Artist changeArtistName(@RequestBody Artist artist){
       return artistService.changeName(artist);
    }

    @PutMapping("/{id}/addComics")
    public Artist changeComics(@PathVariable int id, @RequestBody ComicIdsRequest comicsIds){
        return artistService.changeComics(id,comicsIds);
    }
//    @PutMapping("/{uuid}/removeComics")
//    public ArtistResponse removeComics(@PathVariable int uuid, @RequestBody ComicIdsRequest comicsIds){
//        return artistService.removeComics(uuid,comicsIds);
//    }
    @DeleteMapping("/{id}/delete")
    public Optional<Artist> deleteArtist(@PathVariable int id){
        return artistService.delete(id);
    }
}
