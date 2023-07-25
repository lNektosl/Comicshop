package com.daniil.comicshop.service;

import com.daniil.comicshop.entity.dto.request.AuthorRequest;
import com.daniil.comicshop.entity.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.dto.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse add(AuthorRequest authorRequest);
    AuthorResponse getById(int id);
    List<AuthorResponse> getAll();
    AuthorResponse changeName(int id, AuthorRequest authorRequest);
    AuthorResponse addComics(int id, ComicIdsRequest comicIds);
    AuthorResponse removeComics(int id, ComicIdsRequest comicIds);
    String delete(int id);
}
