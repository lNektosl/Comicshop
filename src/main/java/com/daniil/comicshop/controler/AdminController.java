package com.daniil.comicshop.controler;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ComicService comicService;
    private final ArtistService artistService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final SeriesService seriesService;
    @GetMapping("comic")
    private String comic(Model model){
        model.addAttribute("comics",comicService.getAll());
        return "admin/comic-page";
    }
    @GetMapping("comic/add")
    public String add(Model model){
        model.addAttribute("series",seriesService.getAll());
        model.addAttribute("publishers",publisherService.getAll());
        model.addAttribute("authors",authorService.getAll());
        model.addAttribute("artists", artistService.getAll());
        model.addAttribute("comic",new Comic());
        return "admin/comic-form";
    }
    @PostMapping("comic/add")
    public String add(Comic comic, MultipartFile img, RedirectAttributes ra){
        Comic newComic = comicService.add(img,comic);
        ra.addFlashAttribute("newComic",newComic);
        return "redirect:/admin/comic";
    }
}
