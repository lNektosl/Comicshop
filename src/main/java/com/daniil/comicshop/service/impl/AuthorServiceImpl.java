package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.dto.response.ModelResponse;
import com.daniil.comicshop.service.ModelService;
import com.daniil.comicshop.mapper.AuthorMapper;
import com.daniil.comicshop.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements ModelService<Author> {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;
    @Override
    public ModelResponse getById(int id) {
        return mapper.authorToModelResponse(authorRepository.findById(id).orElseThrow());
    }
}
