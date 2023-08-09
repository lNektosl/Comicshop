package com.daniil.comicshop.controler;

import com.daniil.comicshop.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ComicRepository comicRepository;
    @GetMapping("/")
    public String test(Model model){
        model.addAttribute("comics",comicRepository.findAll());
        return "index";
    }
}
