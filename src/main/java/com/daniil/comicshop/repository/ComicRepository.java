package com.daniil.comicshop.repository;

import com.daniil.comicshop.entity.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic,Integer> {
}
