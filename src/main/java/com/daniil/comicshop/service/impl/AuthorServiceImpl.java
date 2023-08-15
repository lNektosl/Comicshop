package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

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
    public Author change(Author author) {
        if (authorRepository.findById(author.getId()).isPresent()){
        return authorRepository.save(author);}
        throw new NoSuchElementException();
    }


    @Override
    public Optional<Author> delete(int id) {
        Optional<Author> author = getById(id);
        authorRepository.deleteById(id);
        return author;
    }
}
