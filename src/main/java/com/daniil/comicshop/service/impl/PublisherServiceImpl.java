package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.dto.response.PublisherResponse;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.mapper.PublisherMapper;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final ComicRepository comicRepository;
    private final PublisherMapper mapper;

    @Override
    public Publisher add(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Optional<Publisher> getById(int id) {
        return publisherRepository.findById(id);
    }

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher changeName(Publisher publisher) {
        if (publisherRepository.findById(publisher.getId()).isPresent()){
        return publisherRepository.save(publisher);}
        throw new NoSuchElementException();
    }

    @Override
    public Publisher changeComics(int id, ComicIdsRequest comicIds) {
        Publisher publisher = getById(id).get();
        Set<Comic> comics = new HashSet<>(comicRepository.findAllById(comicIds.comicsIds()));
        publisher.setComics(comics);
        return publisherRepository.save(publisher);
    }

//    @Override
//    public Optional<Publisher> removeComics(int uuid, ComicIdsRequest comicIds) {
//        Publisher publisher = findById(uuid);
//        Set<Comic> comics = publisher.getComics();
//        comicRepository.findAllById(comicIds.comicsIds()).forEach(comics::remove);
//        publisher.setComics(comics);
//        publisherRepository.save(publisher);
//        return mapper.publisherToPublisherResponse(publisher);
//    }

    @Override
    public Optional<Publisher> delete(int id) {
        Optional<Publisher> publisher = getById(id);
        publisherRepository.deleteById(id);
        return publisher;
    }
}

