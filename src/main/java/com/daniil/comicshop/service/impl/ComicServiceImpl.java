package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Comic;
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

    @Override
    public Optional<Comic> getById(int id) {
        return comicRepository.findById(id);
    }

    @Override
    public Comic add(MultipartFile img,Comic comic) {
        comic = comicRepository.save(comic);
        if(img != null && !img.isEmpty()){
            try {
                String name = comic.getName().replace(" ","_");
                String fPath = "src/main/resources/static/images/" + name;
                String relPath = "/images/" + name;
                Files.createDirectories(Paths.get(fPath));

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(img.getBytes());
                BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

                ImageIO.write(bufferedImage, "jpg", new File(fPath + "/"
                        + name + ".jpg"));

                comic.setImagePath(relPath + "/" + name + ".jpg");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return comicRepository.save(comic);
        }
        return comic;
    }

    @Override
    public Comic changeImg(int id, MultipartFile img) throws IOException {
        Comic comic = comicRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                String.format("Comic with uuid %s doesn't exist", id)
        ));
        String name = comic.getName().replace(" ","_");
        String fPath = "src/main/resources/static/images/" + name;
        String relPath = "/images/" + name;
        Files.createDirectories(Paths.get(fPath));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(img.getBytes());
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        ImageIO.write(bufferedImage, "jpg", new File(fPath + "/"
                + name + ".jpg"));

        comic.setImagePath(relPath + "/" + name + ".jpg");
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
        if (comicRepository.findById(comic.getId()).isPresent()){
        return comicRepository.save(comic);}
    throw new NoSuchElementException();
    }

    @Override
    public List<Comic> getAll() {
        return comicRepository.findAll();
    }
}
