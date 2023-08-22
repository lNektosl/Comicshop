package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/comic")
@RequiredArgsConstructor
public class ComicController {
    private final ComicService comicService;

    @GetMapping("{id}")
    public String info(@PathVariable Integer id, Model model){
        Optional<Comic> comic = comicService.getById(id);
        if (comic.isPresent()) {
            model.addAttribute("comic", comic.get());
            model.addAttribute("item", new CartItem());
            return "comic";
        }
        return "redirect: /";
    }

}
