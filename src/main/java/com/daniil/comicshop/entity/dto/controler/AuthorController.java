package com.daniil.comicshop.entity.dto.controler;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.dto.response.ModelResponse;
import com.daniil.comicshop.entity.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final ModelService<Author> service;

    @GetMapping("/{id}")
    public ModelResponse getById(@PathVariable int id){
        return service.getById(id);
    }
}
