package com.daniil.comicshop.service.author;

import com.daniil.comicshop.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author add(Author author);
    Optional<Author> getById(int id);
    List<Author> getAll();
    Author change(Author author);
    Optional<Author> delete(int id);
}
