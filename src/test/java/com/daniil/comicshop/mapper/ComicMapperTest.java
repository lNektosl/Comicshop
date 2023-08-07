//package com.daniil.comicshop.mapper;
//
//import com.daniil.comicshop.entity.Artist;
//import com.daniil.comicshop.entity.Author;
//import com.daniil.comicshop.entity.Comic;
//import com.daniil.comicshop.entity.Publisher;
//import com.daniil.comicshop.entity.Series;
//import com.daniil.comicshop.dto.request.ComicRequest;
//import com.daniil.comicshop.dto.response.ComicResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ComicMapperTest {
//
//    private Artist artist;
//    private Publisher publisher;
//    private Author author;
//    private Series series;
//
//    @BeforeEach
//    void init(){
//        artist = Artist.builder()
//                .uuid(1)
//                .name("artist")
//                .build();
//
//        publisher = Publisher.builder()
//                .uuid(1)
//                .name("publisher")
//                .build();
//
//        author = Author.builder()
//                .uuid(1)
//                .name("author")
//                .build();
//
//        series = Series.builder()
//                .uuid(1)
//                .name("series")
//                .build();
//    }
//
//    @Test
//    void comicRequestToComic_shouldBeSuccessful() {
//
//        ComicRequest comicRequest = ComicRequest.builder()
//                .name("Batman")
//                .amount(25)
//                .build();
//
//        Comic expected = Comic.builder()
//                .name("Batman")
//                .amount(25)
//                .build();
//
//
//        ComicMapper mapper = Mappers.getMapper(ComicMapper.class);
//        Comic actual = mapper.comicRequestToComic(comicRequest);
//
//        assertEquals(expected.getName(),actual.getName());
//        assertEquals(expected.getAmount(), actual.getAmount());
//    }
//
//    @Test
//    void comicToComicResponse_shouldBeSuccessful() {
//        Comic comic = Comic.builder()
//                .name("Batman")
//                .amount(25)
//                .authors(List.of(author))
//                .artists(List.of(artist))
//                .publisher(publisher)
//                .series(series)
//                .build();
//
//        ComicResponse expected = ComicResponse.builder()
//                .name("Batman")
//                .amount(25)
//                .seriesId(1)
//                .artistIds(List.of(1))
//                .authorIds(List.of(1))
//                .publisherId(1)
//                .imagePath("src/main/resources/images/1.jpg")
//                .build();
//
//        ComicMapper mapper = Mappers.getMapper(ComicMapper.class);
//
//        ComicResponse actual = mapper.comicToComicResponse(comic);
//
//        assertEquals(expected.getId(),actual.getId());
//        assertEquals(expected.getName(),actual.getName());
//        assertEquals(expected.getAmount(),actual.getAmount());
//        assertEquals(expected.getArtistIds(),actual.getArtistIds());
//        assertEquals(expected.getAuthorIds(),actual.getAuthorIds());
//        assertEquals(expected.getPublisherId(),actual.getPublisherId());
//        assertEquals(expected.getSeriesId(),actual.getSeriesId());
//    }
//
//}