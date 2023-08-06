package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.service.ArtistService;
import com.daniil.comicshop.repository.ArtistRepository;
import com.daniil.comicshop.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ComicRepository comicRepository;

    @Override
    public Artist add(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Optional<Artist> getById(int id) {
        return artistRepository.findById(id);
    }

    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist changeName(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist changeComics(int id, ComicIdsRequest comicIds) {
        Artist artist = getById(id).get();
        Set<Comic> comics = new HashSet<>(comicRepository.findAllById(comicIds.comicsIds()));
        artist.setComics(comics);
        return artistRepository.save(artist);
    }

//    @Override
//    public Optional<Artist> removeComics(int id, ComicIdsRequest comicIds) {
//        Artist artist = findArtistById(id);
//        Set<Comic> comics = artist.getComics();
//        comicRepository.findAllById(comicIds.comicsIds()).forEach(comics::remove);
//        artist.setComics(comics);
//        artistRepository.save(artist);
//        return artistMapper.artistToArtistResponse(artist);
//    }

    @Override
    public Optional<Artist> delete(int id) {
        Optional<Artist> artist = getById(id);
        artistRepository.deleteById(id);
        return artist;
    }
}
