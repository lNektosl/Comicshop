package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.dto.request.ArtistRequest;
import com.daniil.comicshop.entity.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.dto.response.ArtistResponse;
import com.daniil.comicshop.service.ArtistService;
import com.daniil.comicshop.mapper.ArtistMapper;
import com.daniil.comicshop.repository.ArtistRepository;
import com.daniil.comicshop.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ComicRepository comicRepository;
    private final ArtistMapper artistMapper;

    @Override
    public ArtistResponse add(ArtistRequest artistRequest) {
        Artist artist = artistMapper.artistRequestToArtist(artistRequest);
        artistRepository.save(artist);
        return artistMapper.artistToArtistResponse(artist);
    }

    @Override
    public ArtistResponse getById(int id) {
        return artistMapper.artistToArtistResponse(artistRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(
                        String.format("Artist with id %s doesn't exist", id))));
    }

    @Override
    public List<ArtistResponse> getAll() {
        return artistRepository.findAll().stream()
                .map(artistMapper::artistToArtistResponse)
                .toList();
    }

    @Override
    public ArtistResponse changeName(int id, ArtistRequest artistRequest) {
        Artist artist = findArtistById(id);
        artist.setName(artistRequest.name());
        artistRepository.save(artist);
        return artistMapper.artistToArtistResponse(artist);
    }

    @Override
    public ArtistResponse addComics(int id, ComicIdsRequest comicIds) {
        Artist artist = findArtistById(id);
        Set<Comic> comics = artist.getComics();
        comics.addAll(comicRepository.findAllById(comicIds.comicsIds()));
        artist.setComics(comics);
        artistRepository.save(artist);
        return artistMapper.artistToArtistResponse(artist);
    }

    @Override
    public ArtistResponse removeComics(int id, ComicIdsRequest comicIds) {
        Artist artist = findArtistById(id);
        Set<Comic> comics = artist.getComics();
        comicRepository.findAllById(comicIds.comicsIds()).forEach(comics::remove);
        artist.setComics(comics);
        artistRepository.save(artist);
        return artistMapper.artistToArtistResponse(artist);
    }

    @Override
    public String delete(int id) {
        artistRepository.deleteById(id);
        return String.format("Artist with id - %s was deleted",id);
    }

    private Artist findArtistById(int id){
        return artistRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(
                        String.format("Artist with id %s doesn't exist", id)));
    }

}
