package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.service.ArtistService;
import com.daniil.comicshop.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

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
    public Artist change(Artist artist) {
        if (artistRepository.findById(artist.getId()).isPresent()){
        return artistRepository.save(artist);}
        throw new NoSuchElementException();
    }
    @Override
    public Optional<Artist> delete(int id) {
        Optional<Artist> artist = getById(id);
        artistRepository.deleteById(id);
        return artist;
    }
}
