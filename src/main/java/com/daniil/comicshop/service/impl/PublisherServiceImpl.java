package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.dto.request.PublisherRequest;
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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final ComicRepository comicRepository;
    private final PublisherMapper mapper;

    @Override
    public PublisherResponse add(PublisherRequest publisherRequest) {
        return mapper.publisherToPublisherResponse(
                publisherRepository.save(
                        mapper.publisherRequestToPublisher(publisherRequest)));
    }

    @Override
    public PublisherResponse getById(int id) {
        return mapper.publisherToPublisherResponse(publisherRepository.findById(id).orElseThrow());
    }

    @Override
    public List<PublisherResponse> getAll() {
        return publisherRepository.findAll().stream().map(mapper::publisherToPublisherResponse).toList();
    }

    @Override
    public PublisherResponse changeName(int id, PublisherRequest publisherRequest) {
        Publisher publisher = findById(id);
        publisher.setName(publisherRequest.name());
        publisherRepository.save(publisher);
        return mapper.publisherToPublisherResponse(publisher);
    }
    @Override
    public PublisherResponse addComics(int id, ComicIdsRequest comicIds) {
        Publisher publisher = findById(id);
        Set<Comic> comics = publisher.getComics();
        comics.addAll(comicRepository.findAllById(comicIds.comicsIds()));
        publisher.setComics(comics);
        publisherRepository.save(publisher);
        return mapper.publisherToPublisherResponse(publisher);
    }

    @Override
    public PublisherResponse removeComics(int id, ComicIdsRequest comicIds) {
        Publisher publisher = findById(id);
        Set<Comic> comics = publisher.getComics();
        comicRepository.findAllById(comicIds.comicsIds()).forEach(comics::remove);
        publisher.setComics(comics);
        publisherRepository.save(publisher);
        return mapper.publisherToPublisherResponse(publisher);
    }

    @Override
    public PublisherResponse delete(int id) {
        PublisherResponse publisherResponse = mapper.publisherToPublisherResponse(findById(id));
        publisherRepository.deleteById(id);
        return publisherResponse;
    }

    private Publisher findById(Integer id){
        return publisherRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(
                        String.format("Publisher with id %s doesn't exist", id)));
    }
}

