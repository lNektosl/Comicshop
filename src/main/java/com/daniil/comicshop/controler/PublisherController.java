package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.dto.response.PublisherResponse;
import com.daniil.comicshop.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService service;

    @GetMapping("/{id}")
    public PublisherResponse getById(@PathVariable int id){
        return service.getById(id);
    }
}
