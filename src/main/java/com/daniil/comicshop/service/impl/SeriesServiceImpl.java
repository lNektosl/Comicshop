package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.SeriesRepository;
import com.daniil.comicshop.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final ComicRepository comicRepository;

    @Override
    public Series add(Series series) {
        return seriesRepository.save(series);
    }

    @Override
    public Optional<Series> getById(int id) {
        return seriesRepository.findById(id);
    }

    @Override
    public List<Series> getAll() {
        return seriesRepository.findAll();
    }

    @Override
    public Series changeName(Series series) {
        if (seriesRepository.findById(series.getId()).isPresent()){
            return seriesRepository.save(series);}
        throw new NoSuchElementException();
    }

    @Override
    public Series changeComics(int id, ComicIdsRequest comicIds) {
        Series series = getById(id).get();
        Set<Comic> comics = new HashSet<>(comicRepository.findAllById(comicIds.comicsIds()));
        series.setComics(comics);
        return seriesRepository.save(series);
    }

    @Override
    public Optional<Series> delete(int id) {
        Optional<Series> series = getById(id);
        seriesRepository.deleteById(id);
        return series;
    }
}
