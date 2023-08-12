package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.Series;

import java.util.List;
import java.util.Optional;

public interface SeriesService {
        Series add(Series series);
        Optional<Series> getById(int id);
        List<Series> getAll();
        Series changeName(Series series);
        Series changeComics(int id, ComicIdsRequest comicIds);
        Optional<Series> delete(int id);
}
