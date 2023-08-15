package com.daniil.comicshop.service.impl;

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
    public Series change(Series series) {
        Optional<Series> oldSeries = seriesRepository.findById(series.getId());
        if (oldSeries.isPresent()){
            oldSeries.get().getComics().removeAll(series.getComics());
            oldSeries.get().getComics().forEach(series::removeComic);
            return seriesRepository.save(series);}
        throw new NoSuchElementException();
    }

    @Override
    public Optional<Series> delete(int id) {
        Optional<Series> series = getById(id);
        seriesRepository.deleteById(id);
        return series;
    }
}
