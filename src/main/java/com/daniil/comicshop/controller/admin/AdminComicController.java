package com.daniil.comicshop.controller.admin;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.artist.ArtistService;
import com.daniil.comicshop.service.author.AuthorService;
import com.daniil.comicshop.service.comic.ComicService;
import com.daniil.comicshop.service.publisher.PublisherService;
import com.daniil.comicshop.service.series.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/comic")
@RequiredArgsConstructor
public class AdminComicController {
    private final ComicService comicService;
    private final ArtistService artistService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final SeriesService seriesService;
    @GetMapping("")
    private String comic(Model model){
        model.addAttribute("comics",comicService.getAll());
        return "admin/comic/comic-page";
    }
    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("series",seriesService.getAll());
        model.addAttribute("publishers",publisherService.getAll());
        model.addAttribute("authors",authorService.getAll());
        model.addAttribute("artists", artistService.getAll());
        model.addAttribute("comic",new Comic());
        return "admin/comic/comic-form";
    }
    @PostMapping("add")
    public String add(Comic comic, MultipartFile img, RedirectAttributes ra){
        Comic newComic = comicService.add(img,comic);
        ra.addFlashAttribute("newComic",newComic);
        return "redirect:/admin/comic";
    }

    @GetMapping("{id}")
    public String info(@PathVariable Integer id, Model model){
        Optional<Comic> comic = comicService.getById(id);
        if (comic.isPresent()){
            model.addAttribute("comic",comic.get());
            return "admin/comic/comic";
        }
        return "redirect:/admin/comic";
    }
    @GetMapping("{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes ra) throws IOException {
        ra.addFlashAttribute("deletedComics",comicService.delete(id).get());
        return "redirect:/admin/comic";
    }
    @GetMapping("{id}/change")
    public String change(@PathVariable Integer id, Model model){
        Optional<Comic> comic= comicService.getById(id);
        if (comic.isPresent()){
            model.addAttribute("series",seriesService.getAll());
            model.addAttribute("publishers",publisherService.getAll());
            model.addAttribute("authors",authorService.getAll());
            model.addAttribute("artists", artistService.getAll());
            model.addAttribute("comic",comic.get());
            return "admin/comic/comic-changeForm";
        }
        return "redirect:/admin/comic";
    }
    @PostMapping("{id}/change")
    public String change(MultipartFile img,Comic comic,RedirectAttributes ra) throws IOException {

        ra.addFlashAttribute("updatedComic",comicService.change(img,comic));
        return "redirect:/admin/comic";
    }
}
