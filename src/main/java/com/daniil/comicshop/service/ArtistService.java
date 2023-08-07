package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Artist add(Artist artist);
    Optional<Artist> getById(int id);
    List<Artist> getAll();
    Artist changeName(Artist artist);
    Artist changeComics(int id, ComicIdsRequest comicIds);
//    Optional<Artist> removeComics(int uuid, ComicIdsRequest comicIds);
    Optional<Artist> delete(int id);
}
