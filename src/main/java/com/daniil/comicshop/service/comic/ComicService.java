package com.daniil.comicshop.service.comic;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ComicService {
    Optional<Comic> getById(int id);
    Comic add(MultipartFile img,Comic comic);
    Optional<Comic> delete(int id) throws IOException;
    Comic change(MultipartFile img,Comic comic) throws IOException;
    Page<Comic> getPage(Integer pageNum);
    Page<Comic> getPageByAuthor(Author author, Pageable pageable);
    Page<Comic> getPageByArtist(Artist artist, Pageable pageable);
    Page<Comic> getPageBySeries(Series series, Pageable pageable);
    List<Comic> getAll();
}
