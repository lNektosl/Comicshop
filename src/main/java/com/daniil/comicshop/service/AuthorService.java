package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.AuthorRequest;
import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.dto.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse add(AuthorRequest authorRequest);
    AuthorResponse getById(int id);
    List<AuthorResponse> getAll();
    AuthorResponse changeName(int id, AuthorRequest authorRequest);
    AuthorResponse addComics(int id, ComicIdsRequest comicIds);
    AuthorResponse removeComics(int id, ComicIdsRequest comicIds);
    AuthorResponse delete(int id);
}
