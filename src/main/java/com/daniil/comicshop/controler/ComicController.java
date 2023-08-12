package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comic")
@RequiredArgsConstructor
public class ComicController {
    private final ComicService comicService;
    private final ArtistService artistService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final SeriesService seriesService;
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("series",seriesService.getAll());
        model.addAttribute("publishers",publisherService.getAll());
        model.addAttribute("authors",authorService.getAll());
        model.addAttribute("artists", artistService.getAll());
        model.addAttribute("comic",new Comic());
        return "admin/comic-form";
    }
    @PostMapping("/add")
    public String add(Comic comic, MultipartFile img, RedirectAttributes ra){
        Comic newComic = comicService.add(img,comic);
        ra.addFlashAttribute("newComic",newComic);
        return "redirect:/admin";
    }
}
