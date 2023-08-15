package com.daniil.comicshop.controller;

import com.daniil.comicshop.service.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ComicService comicService;
    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("comics",comicService.getAll());
        return "index";
    }
    @GetMapping("/admin")
    public String adminMenu(){
        return "admin/admin-menu";
    }
}
