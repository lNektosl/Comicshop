package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.dto.request.PublisherRequest;
import com.daniil.comicshop.dto.response.PublisherResponse;

import java.util.List;

public interface PublisherService {
    PublisherResponse add(PublisherRequest publisherRequest);
    PublisherResponse getById(int id);
    List<PublisherResponse> getAll();
    PublisherResponse changeName(int id, PublisherRequest publisherRequest);
    PublisherResponse addComics(int id, ComicIdsRequest comicIds);
    PublisherResponse removeComics(int id, ComicIdsRequest comicIds);
    PublisherResponse delete(int id);
}
