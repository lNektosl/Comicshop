package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.dto.response.AuthorResponse;
import com.daniil.comicshop.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService service;

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable int id){
        return service.getById(id);
    }
}
