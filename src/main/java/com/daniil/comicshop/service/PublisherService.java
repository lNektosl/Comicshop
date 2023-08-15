package com.daniil.comicshop.service;

import com.daniil.comicshop.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    Publisher add(Publisher publisher);
    Optional<Publisher> getById(int id);
    List<Publisher> getAll();
    Publisher change(Publisher publisher);
    Optional<Publisher> delete(int id);
}
