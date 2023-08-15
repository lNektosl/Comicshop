package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.ClientRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.repository.SeriesRepository;
import com.daniil.comicshop.service.ClientService;
import com.daniil.comicshop.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class test {
    private final ComicRepository comicRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final SeriesRepository seriesRepository;
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final ComicService comicService;

    @GetMapping("comic")
    public List<Comic> test(){
        return comicService.getAll();
    }
    @PostMapping("comic")
    public Comic comic(@RequestBody Comic comic){
        return comicRepository.save(comic);
    }
}
