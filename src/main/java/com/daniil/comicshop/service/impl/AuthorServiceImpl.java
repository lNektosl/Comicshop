package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.dto.request.AuthorRequest;
import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.dto.response.AuthorResponse;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.mapper.AuthorMapper;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ComicRepository comicRepository;
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
        return authorRepository.findAll()
                .stream()
                .map(mapper::authorToAuthorResponse)
                .toList();
    }

    @Override
    public AuthorResponse changeName(int id, AuthorRequest authorRequest) {
        Author author = findById(id);
        author.setName(authorRequest.name());
        authorRepository.save(author);
        return mapper.authorToAuthorResponse(author);
    }

    @Override
    public AuthorResponse addComics(int id, ComicIdsRequest comicIds) {
        Author author = findById(id);
        Set<Comic> comics = author.getComics();
        comics.addAll(comicRepository.findAllById(comicIds.comicsIds()));
        author.setComics(comics);
        authorRepository.save(author);
        return mapper.authorToAuthorResponse(author);
    }

    @Override
    public AuthorResponse removeComics(int id, ComicIdsRequest comicIds) {
        Author author = findById(id);
        Set<Comic> comics = author.getComics();
        comicRepository.findAllById(comicIds.comicsIds()).forEach(comics::remove);
        author.setComics(comics);
        authorRepository.save(author);
        return mapper.authorToAuthorResponse(author);
    }

    @Override
    public AuthorResponse delete(int id) {
        AuthorResponse authorResponse = mapper.authorToAuthorResponse(findById(id));
        authorRepository.deleteById(id);
        return authorResponse;
    }

    private Author findById(Integer id){
        return authorRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(
                        String.format("Author with id %s doesn't exist", id)));
    }
}
