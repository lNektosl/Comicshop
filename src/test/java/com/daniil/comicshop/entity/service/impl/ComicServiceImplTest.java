package com.daniil.comicshop.entity.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.entity.dto.request.ComicRequest;
import com.daniil.comicshop.entity.dto.response.ComicResponse;
import com.daniil.comicshop.mapper.ComicMapper;
import com.daniil.comicshop.repository.ArtistRepository;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.repository.SeriesRepository;
import com.daniil.comicshop.service.impl.ComicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComicServiceImplTest {

    @Mock
    private ComicRepository comicRepository;
    @Mock
    private PublisherRepository publisherRepository;
    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private SeriesRepository seriesRepository;
    @Mock
    private ComicMapper comicMapper;

    @InjectMocks
    private ComicServiceImpl comicService;


    private Artist artist;
    private Publisher publisher;
    private Author author;
    private Series series;
    private ComicRequest comicRequest;
    private ComicRequest comicRequest2;
    private ComicResponse comicResponse;
    private Comic comic;

    @BeforeEach
    void init(){
        artist = Artist.builder()
                .name("artist")
                .build();

        publisher = Publisher.builder()
                .name("publisher")
                .build();

        author = Author.builder()
                .name("author")
                .build();

        series = Series.builder()
                .id(1)
                .name("series")
                .build();

        comicRequest = ComicRequest.builder()
                .name("Batman")
                .amount(25)
                .seriesId(1)
                .artistIds(List.of(1))
                .authorIds(List.of(1))
                .publisherId(1)
                .build();

        comicRequest2 = ComicRequest.builder()
                .name("Joker")
                .amount(5)
                .seriesId(1)
                .artistIds(List.of(1))
                .authorIds(List.of(1))
                .publisherId(1)
                .build();

        comicResponse = ComicResponse.builder()
                .name("Batman")
                .amount(25)
                .seriesId(1)
                .artistIds(List.of(1))
                .authorIds(List.of(1))
                .publisherId(1)
                .imagePath("src/main/resources/images/1.jpg")
                .build();

        comic = Comic.builder()
                .id(1)
                .name("Batman")
                .amount(25)
                .series(series)
                .publisher(publisher)
                .artists(List.of(artist))
                .authors(List.of(author))
                .imagePath("src/main/resources/images/1.jpg")
                .build();
    }


    @Test
    void getComicById_shouldBeSuccessful() {
        when(comicMapper.comicToComicResponse(comic)).thenReturn(comicResponse);
        when(comicRepository.findById(any())).thenReturn(Optional.of(comic));

            ComicResponse actualResponse = comicService.getComicById(comic.getId());

        assertEquals(comicResponse,actualResponse);


    }

    @Test
    void saveComic_shouldBeSuccessful() {
        when(publisherRepository.findById(any())).thenReturn(Optional.of(publisher));
        when(seriesRepository.findById(any())).thenReturn(Optional.of(series));
        when(comicMapper.comicRequestToComic(comicRequest)).thenReturn(comic);
        when(comicMapper.comicToComicResponse(comic)).thenReturn(comicResponse);

        assertEquals(comicResponse,comicService.saveComic(comicRequest));

    }

    @Test
    void changeImg() throws IOException {

    }

    @Test
    void changeById() {
        when(publisherRepository.findById(any())).thenReturn(Optional.of(publisher));
        when(seriesRepository.findById(any())).thenReturn(Optional.of(series));
        when(comicRepository.findById(any())).thenReturn(Optional.of(comic));
        when(comicMapper.comicToComicResponse(comic)).thenReturn(comicResponse);

        assertEquals(comicResponse,comicService.changeById(comic.getId(),comicRequest2));
    }
}