package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Cart;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.CartRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class test {
    private final ComicRepository comicRepository;
    private final CartRepository cartRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final SeriesRepository seriesRepository;

    @GetMapping("/comic")
    public List<Comic> test(){
        return comicRepository.findAll();
    }

    @GetMapping("/cart")
    public List<Cart> test2(){
        return cartRepository.findAll();
    }

    @GetMapping("/publisher")
    public List<Publisher> publishers(){
        return publisherRepository.findAll();
    }

    @GetMapping("/author")
    public List<Author> test4(){return authorRepository.findAll();}
    @GetMapping("/series")
    public List<Series> test5(){
        return seriesRepository.findAll();
    }
}
