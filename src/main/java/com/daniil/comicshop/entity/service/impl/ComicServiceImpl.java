package com.daniil.comicshop.entity.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.entity.dto.request.ComicRequest;
import com.daniil.comicshop.entity.dto.response.ComicResponse;
import com.daniil.comicshop.entity.service.ComicService;
import com.daniil.comicshop.mapper.ComicMapper;
import com.daniil.comicshop.repository.ArtistRepository;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ComicServiceImpl implements ComicService {

    private final ComicRepository comicRepository;
    private final SeriesRepository seriesRepository;
    private final ComicMapper comicMapper;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final ArtistRepository artistRepository;

    @Override
    public ComicResponse getComicById(int id) {
        Comic comic = comicRepository.findById(id).orElseThrow();
        return comicMapper.comicToComicResponse(comic);
    }

    @Override
    public ComicResponse saveComic(ComicRequest comicRequest) {

        Comic comic = comicMapper.comicRequestToComic(comicRequest);

        Publisher publisher = publisherRepository.findById(comicRequest.getPublisherId()).orElseThrow(() ->
                new NoSuchElementException(String.format("Publisher with id %s doesn't exist", comicRequest.getSeriesId())));
        if (comicRequest.getSeriesId() != 0) {
            Series series = seriesRepository.findById(comicRequest.getSeriesId()).orElseThrow(() ->
                    new NoSuchElementException(String.format("Series with id %s doesn't exist", comicRequest.getSeriesId())));
            comic.setSeries(series);
        }

        comic.setPublisher(publisher);

        setAuthorsAndArtists(comic,
                artistRepository.findAllById(comicRequest.getArtistIds()),
                authorRepository.findAllById(comicRequest.getAuthorIds()));


        return comicMapper.comicToComicResponse(comic);
    }

    @Override
    public ComicResponse changeImg(int id, MultipartFile img) throws IOException {
        Comic comic = comicRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                String.format("Comic with id %s doesn't exist", id)
        ));
        String name = comic.getName();
        String path = "src/main/resources/images/" + name;
        path = path.replace(" ", "_");
        Files.createDirectories(Paths.get(path));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(img.getBytes());
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        ImageIO.write(bufferedImage, "jpg", new File(path + "/" + name + ".jpg"));

        comic.setImagePath(path);
        return comicMapper.comicToComicResponse(comicRepository.save(comic));
    }

    @Override
    public String deleteById(int id) {
        comicRepository.deleteById(id);
        return "Comic with id - " + id + " was deleted";
    }

    @Override
    public ComicResponse changeById(int id, ComicRequest request) {
        Comic comic = comicRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                String.format("Comic with id %s doesn't exist", id)
        ));
        comic.setName(request.getName());
        comic.setAmount(request.getAmount());

        comic.setPublisher(publisherRepository.findById(request.getPublisherId()).orElseThrow());
        comic.setSeries(seriesRepository.findById(request.getPublisherId()).orElseThrow());

        setAuthorsAndArtists(comic,
                artistRepository.findAllById(request.getArtistIds()),
                authorRepository.findAllById(request.getAuthorIds()));

        return comicMapper.comicToComicResponse(comic);
    }

    @Override
    public List<ComicResponse> getAllComic() {
        return comicRepository.findAll().stream()
                .map(comicMapper::comicToComicResponse)
                .toList();
    }

    private void setAuthorsAndArtists(Comic comic, List<Artist> artists, List<Author> authors) {

        comic.setArtists(artists);
        comic.setAuthors(authors);

        Comic savedComic = comicRepository.save(comic);

        for (Author author : authors) {
            author.getComics().add(savedComic);
            authorRepository.save(author);
        }

        for (Artist artist : artists) {
            artist.getComics().add(savedComic);
            artistRepository.save(artist);
        }
    }
}
