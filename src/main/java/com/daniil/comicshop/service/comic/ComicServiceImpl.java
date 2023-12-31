package com.daniil.comicshop.service.comic;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.service.comic.ComicService;
import com.daniil.comicshop.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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
    public Comic add(MultipartFile img, Comic comic) {
        comic.setDate(LocalDate.now());
        comic = comicRepository.save(comic);
        if (img != null && !img.isEmpty()) {
            try {
                changeImg(comic, img);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return comicRepository.save(comic);
        }
        return comic;
    }


    @Override
    public Optional<Comic> delete(int id) throws IOException {
        Optional<Comic> comic = comicRepository.findById(id);
        if (comic.isPresent() && !Objects.equals(comic.get().getImagePath(),"/images/1.jpg")) {
            Path imgPath = Path.of("src/main/resources/static" +  comic.get().getImagePath());
            Files.delete(imgPath);
            Files.delete(imgPath.getParent());
        }
        comicRepository.deleteById(id);
        return comic;
    }

    @Override
    public Comic change(MultipartFile img, Comic comic) throws IOException {
        if (comicRepository.findById(comic.getId()).isPresent()) {
            Comic oldComic = comicRepository.findById(comic.getId()).get();
            if (img != null && !img.isEmpty()) {
                changeImg(comic, img);
            } else comic.setImagePath(oldComic.getImagePath());
            return comicRepository.save(comic);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Page<Comic> getPage(Integer pageNum) {
        return comicRepository.findAll(PageRequest.of(pageNum, 9));
    }

    @Override
    public Page<Comic> getPageByAuthor(Author author, Pageable pageable) {
        return comicRepository.getComicByAuthors(author, pageable);
    }

    @Override
    public Page<Comic> getPageByArtist(Artist artist, Pageable pageable) {
        return comicRepository.getComicByArtists(artist, pageable);
    }

    @Override
    public Page<Comic> getPageBySeries(Series series, Pageable pageable) {
        return comicRepository.getComicBySeries(series, pageable);
    }

    public List<Comic> getAll() {
        return comicRepository.findAll();
    }


    private void changeImg(Comic comic, MultipartFile img) throws IOException {
        String name = comic.getName().replace(" ", "_").replace(":", "-");
        String fPath = "src/main/resources/static/images/" + name;
        String relPath = "/images/" + name;
        Files.createDirectories(Paths.get(fPath));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(img.getBytes());
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

        ImageIO.write(bufferedImage, "jpg", new File(fPath + "/"
                + name + ".jpg"));

        comic.setImagePath(relPath + "/" + name + ".jpg");
    }
}
