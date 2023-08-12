package com.daniil.comicshop.service;

import com.daniil.comicshop.entity.Comic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ComicService {
    Optional<Comic> getById(int id);
    Comic add(MultipartFile img,Comic comic);
    Comic changeImg(int id, MultipartFile img) throws IOException;
    Optional<Comic> delete(int id);
    Comic change(Comic comic);
    List<Comic> getAll();

}
