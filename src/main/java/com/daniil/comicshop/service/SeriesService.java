package com.daniil.comicshop.service;

import com.daniil.comicshop.entity.Series;

import java.util.List;
import java.util.Optional;

public interface SeriesService {
        Series add(Series series);
        Optional<Series> getById(int id);
        List<Series> getAll();
        Series change(Series series);
        Optional<Series> delete(int id);
}
