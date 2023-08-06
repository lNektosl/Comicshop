package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.dto.response.ComicResponse;
import com.daniil.comicshop.service.ComicService;
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
import java.util.Optional;


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
    public Optional<Comic> getById(int id) {
        return comicRepository.findById(id);
    }

    @Override
    public Comic add(Comic comic) {
        return comicRepository.save(comic);
    }

    @Override
    public Comic changeImg(int id, MultipartFile img) throws IOException {
        Comic comic = comicRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                String.format("Comic with id %s doesn't exist", id)
        ));
        String name = comic.getName();
        String path = "src/main/resources/images/" + name;
        path = path.replace(" ", "_");
        Files.createDirectories(Paths.get(path));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(img.getBytes());
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        ImageIO.write(bufferedImage, "jpg", new File(path + "/"
                + name.replace(" ", "_") + ".jpg"));

        comic.setImagePath(path + "/" + name.replace(" ", "_") + ".jpg");
        return comicRepository.save(comic);
    }

    @Override
    public Optional<Comic> delete(int id) {
        Optional<Comic> comic = comicRepository.findById(id);
        comicRepository.deleteById(id);
        return comic;
    }

    @Override
    public Comic change(Comic comic) {
        return comicRepository.save(comic);
    }

    @Override
    public List<Comic> getAll() {
        return comicRepository.findAll();
    }
}
