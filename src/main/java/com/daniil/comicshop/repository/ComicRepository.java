package com.daniil.comicshop.repository;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComicRepository extends JpaRepository<Comic,Integer> {
    Page<Comic> getComicByAuthors(Author author, Pageable pageable);
}
