package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    Publisher add(Publisher publisher);
    Optional<Publisher> getById(int id);
    List<Publisher> getAll();
    Publisher changeName(Publisher publisher);
    Publisher changeComics(int id, ComicIdsRequest comicIds);
//    Optional<Publisher> removeComics(int id, ComicIdsRequest comicIds);
    Optional<Publisher> delete(int id);
}
