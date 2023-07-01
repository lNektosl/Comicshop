package com.daniil.comicshop.repository;

import com.daniil.comicshop.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Integer> {
}
