package com.daniil.comicshop.entity.service.impl;

import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.dto.response.ModelResponse;
import com.daniil.comicshop.entity.service.ModelService;
import com.daniil.comicshop.mapper.PublisherMapper;
import com.daniil.comicshop.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements ModelService<Publisher> {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper mapper;
    @Override
    public ModelResponse getById(int id) {
        return mapper.publisherToModelResponse(publisherRepository.findById(id).orElseThrow());
    }
}
