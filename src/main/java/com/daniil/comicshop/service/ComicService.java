package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.ComicRequest;
import com.daniil.comicshop.dto.response.ComicResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ComicService {
    ComicResponse getComicById(int id);
    ComicResponse saveComic(ComicRequest comicRequest);
    ComicResponse changeImg(int id, MultipartFile img) throws IOException;
    ComicResponse deleteById(int id);
    ComicResponse changeById(int id, ComicRequest request);
    List<ComicResponse> getAllComic();

}
