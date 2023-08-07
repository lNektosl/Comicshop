package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.dto.request.ComicRequest;
import com.daniil.comicshop.dto.response.ComicResponse;
import com.daniil.comicshop.mapper.ComicMapper;
import com.daniil.comicshop.repository.ArtistRepository;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.repository.SeriesRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComicServiceImplTest {

    @Mock
    private ComicRepository comicRepository;
//    @Mock
//    private PublisherRepository publisherRepository;
//    @Mock
//    private ArtistRepository artistRepository;
//    @Mock
//    private AuthorRepository authorRepository;
//    @Mock
//    private SeriesRepository seriesRepository;
    @Mock
    private ComicMapper comicMapper;

    @InjectMocks
    private ComicServiceImpl comicService;


    private ComicResponse comicResponse;
    private Comic comic;

    @BeforeEach
    void init(){
        Artist artist = Artist.builder()
                .name("artist")
                .build();

        Publisher publisher = Publisher.builder()
                .name("publisher")
                .build();

        Author author = Author.builder()
                .name("author")
                .build();

        Series series = Series.builder()
                .id(1)
                .name("series")
                .build();

        ComicRequest comicRequest = ComicRequest.builder()
                .name("Batman")
                .amount(25)
                .seriesId(1)
                .artistIds(List.of(1))
                .authorIds(List.of(1))
                .publisherId(1)
                .build();

        ComicRequest comicRequest2 = ComicRequest.builder()
                .name("Joker")
                .amount(5)
                .seriesId(1)
                .artistIds(List.of(1))
                .authorIds(List.of(1))
                .publisherId(1)
                .build();

        comicResponse = ComicResponse.builder()
                .name("Test test")
                .amount(25)
                .seriesId(1)
                .artistIds(List.of(1))
                .authorIds(List.of(1))
                .publisherId(1)
                .imagePath("src/main/resources/static/images/1.jpg")
                .build();

        comic = Comic.builder()
                .id(1)
                .name("Test test")
                .amount(25)
                .series(series)
                .publisher(publisher)
                .artists(List.of(artist))
                .authors(List.of(author))
                .imagePath("src/main/resources/static/images/1.jpg")
                .build();
    }

    @Test
    void changeImg_shouldBeSuccessful() throws IOException {
        when(comicRepository.findById(anyInt())).thenReturn(Optional.of(comic));

        String path = "src/main/resources/static/images/1.jpg";
        String path2 = "/images/Test_test/Test_test.jpg";
        MultipartFile multipartFile = new MockMultipartFile("test.jpg",new FileInputStream(path));

        comicService.changeImg(1,multipartFile);

        assertEquals(path2,comic.getImagePath());

        assertTrue(Files.exists(Path.of("src/main/resources/static/images/Test_test/Test_test.jpg")));

    }

   @AfterAll
    static void Erase() throws IOException {
        Files.delete(Path.of("src/main/resources/static/images/Test_test/Test_test.jpg"));
        Files.delete(Path.of("src/main/resources/static/images/Test_test"));
    }
}