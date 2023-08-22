package com.daniil.comicshop.service.comic;

import com.daniil.comicshop.entity.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ComicService {
    Optional<Comic> getById(int id);
    Comic add(MultipartFile img,Comic comic);
    Optional<Comic> delete(int id);
    Comic change(MultipartFile img,Comic comic) throws IOException;
    Page<Comic> getPage(Integer pageNum);
    List<Comic> getAll();
}