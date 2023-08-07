package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.dto.response.AuthorResponse;
import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.mapper.AuthorMapper;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ComicRepository comicRepository;
    private final AuthorMapper mapper;

    @Override
    public Author add(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> getById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author changeName(Author author) {
        if (authorRepository.findById(author.getId()).isPresent()){
        return authorRepository.save(author);}
        throw new NoSuchElementException();
    }

    @Override
    public Author changeComics(int id, ComicIdsRequest comicIds) {
        Author author = getById(id).get();
        Set<Comic> comics = new HashSet<>(comicRepository.findAllById(comicIds.comicsIds()));
        author.setComics(comics);
        return authorRepository.save(author);

    }

//    @Override
//    public Optional<Author> removeComics(int uuid, ComicIdsRequest comicIds) {
//        Author author = findById(uuid);
//        Set<Comic> comics = author.getComics();
//        comicRepository.findAllById(comicIds.comicsIds()).forEach(comics::remove);
//        author.setComics(comics);
//        authorRepository.save(author);
//        return mapper.authorToAuthorResponse(author);
//    }

    @Override
    public Optional<Author> delete(int id) {
        Optional<Author> author = getById(id);
        authorRepository.deleteById(id);
        return author;
    }
}
