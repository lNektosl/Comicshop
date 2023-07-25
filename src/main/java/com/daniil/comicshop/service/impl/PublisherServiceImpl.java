package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.dto.request.PublisherRequest;
import com.daniil.comicshop.entity.dto.response.PublisherResponse;
import com.daniil.comicshop.mapper.PublisherMapper;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper mapper;

    @Override
    public PublisherResponse add(PublisherRequest publisherRequest) {
        return null;
    }

    @Override
    public PublisherResponse getById(int id) {
        return mapper.publisherToPublisherResponse(publisherRepository.findById(id).orElseThrow());
    }

    @Override
    public List<PublisherResponse> getAll() {
        return null;
    }

    @Override
    public PublisherResponse changeName(int id, PublisherRequest publisherRequest) {
        return null;
    }

    @Override
    public PublisherResponse addComics(int id, ComicIdsRequest comicIds) {
        return null;
    }

    @Override
    public PublisherResponse removeComics(int id, ComicIdsRequest comicIds) {
        return null;
    }

    @Override
    public String delete(int id) {
        return null;
    }
}

