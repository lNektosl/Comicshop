package com.daniil.comicshop.service;

import com.daniil.comicshop.entity.dto.request.ArtistRequest;
import com.daniil.comicshop.entity.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.dto.response.ArtistResponse;

import java.util.List;

public interface ArtistService {
    ArtistResponse add(ArtistRequest artistRequest);
    ArtistResponse getById(int id);
    List<ArtistResponse> getAll();
    ArtistResponse changeName(int id, ArtistRequest artistRequest);
    ArtistResponse addComics(int id, ComicIdsRequest comicIds);
    ArtistResponse removeComics(int id, ComicIdsRequest comicIds);
    String delete(int id);
}
