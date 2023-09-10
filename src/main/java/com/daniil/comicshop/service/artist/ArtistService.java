package com.daniil.comicshop.service.artist;

import com.daniil.comicshop.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Artist add(Artist artist);
    Optional<Artist> getById(int id);
    List<Artist> getAll();
    Artist change(Artist artist);
    Optional<Artist> delete(int id);
}
