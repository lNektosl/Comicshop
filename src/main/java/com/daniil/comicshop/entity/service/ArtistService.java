package com.daniil.comicshop.entity.service;

import com.daniil.comicshop.entity.dto.request.ArtistRequest;
import com.daniil.comicshop.entity.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.dto.response.ArtistResponse;

import java.util.List;

public interface ArtistService {
    ArtistResponse addArtist(ArtistRequest artistRequest);
    ArtistResponse getArtistById(int id);
    List<ArtistResponse> getAllArtists();
    ArtistResponse changeArtistName(int id,ArtistRequest artistRequest);
    ArtistResponse addComicsToArtist(int id, ComicIdsRequest comicIds);
    ArtistResponse removeComicsFromArtist(int id, ComicIdsRequest comicIds);
    String deleteArtist(int id);
}
