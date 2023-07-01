package com.daniil.comicshop.repository;

import com.daniil.comicshop.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
}
