package com.daniil.comicshop.controler;

import com.daniil.comicshop.repository.AuthorRepository;
import com.daniil.comicshop.repository.ClientRepository;
import com.daniil.comicshop.repository.ComicRepository;
import com.daniil.comicshop.repository.PublisherRepository;
import com.daniil.comicshop.repository.SeriesRepository;
import com.daniil.comicshop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class test {
    private final ComicRepository comicRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final SeriesRepository seriesRepository;
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    @GetMapping("/comic")
    public String test(Model model){
        model.addAttribute("comics", comicRepository.findAll());
        return "index";
    }
}
