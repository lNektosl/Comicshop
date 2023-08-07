package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author add(Author author);
    Optional<Author> getById(int id);
    List<Author> getAll();
    Author changeName(Author author);
    Author changeComics(int id, ComicIdsRequest comicIds);
//    Optional<Author> removeComics(int uuid, ComicIdsRequest comicIds);
    Optional<Author> delete(int id);
}
