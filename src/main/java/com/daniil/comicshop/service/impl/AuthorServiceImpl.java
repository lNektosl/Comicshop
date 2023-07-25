package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.dto.request.AuthorRequest;
import com.daniil.comicshop.entity.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.dto.response.AuthorResponse;
import com.daniil.comicshop.mapper.AuthorMapper;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    @Override
    public AuthorResponse add(AuthorRequest authorRequest) {
        return null;
    }

    @Override
    public AuthorResponse getById(int id) {
        return mapper.authorToAuthorResponse(authorRepository.findById(id).orElseThrow());
    }

    @Override
    public List<AuthorResponse> getAll() {
        return null;
    }

    @Override
    public AuthorResponse changeName(int id, AuthorRequest authorRequest) {
        return null;
    }

    @Override
    public AuthorResponse addComics(int id, ComicIdsRequest comicIds) {
        return null;
    }

    @Override
    public AuthorResponse removeComics(int id, ComicIdsRequest comicIds) {
        return null;
    }

    @Override
    public String delete(int id) {
        return null;
    }
}
