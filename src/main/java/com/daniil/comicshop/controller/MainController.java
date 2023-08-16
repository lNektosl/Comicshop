package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.ComicService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ComicService comicService;

    @GetMapping("/")
    public String getMainPage(Model model, HttpSession session, @RequestParam(required = false,defaultValue = "0") Integer pageNum) {
        Page<Comic> page = comicService.getPage(pageNum);
        model.addAttribute("comics",page.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", page.getTotalPages());
        return "index";
    }

    @GetMapping("/admin")
    public String adminMenu() {
        return "admin/admin-menu";
    }
}
