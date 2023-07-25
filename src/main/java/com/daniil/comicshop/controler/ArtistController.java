package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.dto.request.ArtistRequest;
import com.daniil.comicshop.entity.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.dto.response.ArtistResponse;
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

@RestController
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping
    public List<ArtistResponse> getAllArtists(){
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    public ArtistResponse getById(@PathVariable int id){
        return artistService.getById(id);
    }

    @PostMapping("/add")
    public ArtistResponse addArtist(@RequestBody ArtistRequest artistRequest){
        return artistService.add(artistRequest);
    }

    @PutMapping("/{id}/change")
    public ArtistResponse changeArtistName(@PathVariable int id, @RequestBody ArtistRequest artistRequest){
       return artistService.changeName(id,artistRequest);
    }

    @PutMapping("/{id}/addComics")
    public ArtistResponse addComicToArtist(@PathVariable int id, @RequestBody ComicIdsRequest comicsIds){
        return artistService.addComics(id,comicsIds);
    }
    @PutMapping("/{id}/removeComics")
    public ArtistResponse removeComics(@PathVariable int id, @RequestBody ComicIdsRequest comicsIds){
        return artistService.removeComics(id,comicsIds);
    }
    @DeleteMapping("/{id}/delete")
    public String deleteArtist(@PathVariable int id){
        return artistService.delete(id);
    }
}