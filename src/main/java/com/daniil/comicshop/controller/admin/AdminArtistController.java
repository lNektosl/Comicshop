package com.daniil.comicshop.controller.admin;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.service.artist.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin/artist")
@RequiredArgsConstructor
public class AdminArtistController {
    private final ArtistService artistService;

    @GetMapping
    public String getArtists(Model model) {
        model.addAttribute("artists", artistService.getAll());
        return "admin/artist/artist-page";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable Integer id, Model model) {
        Optional<Artist> artist = artistService.getById(id);
        if (artist.isPresent()) {
            model.addAttribute("artist", artist.get());
            return "admin/artist/artist";
        }
        return "redirect:/admin/artist";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        ra.addFlashAttribute("deletedArtist", artistService.delete(id).get());
        return "redirect:/admin/artist";
    }

    @GetMapping("{id}/change")
    public String change(@PathVariable Integer id, Model model) {
        Optional<Artist> artist = artistService.getById(id);
        if (artist.isPresent()) {
            model.addAttribute("artist", artist.get());
            return "admin/artist/artist-changeForm";
        }
        return "redirect:/admin/artist";
    }

    @PostMapping("{id}/change")
    public String change(RedirectAttributes ra, Artist artist) {
        artistService.change(artist);
        ra.addFlashAttribute("updatedArtist", artistService.change(artist));
        return "redirect:/admin/artist";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("artist", new Artist());
        return "admin/artist/artist-form";
    }

    @PostMapping("add")
    public String add(RedirectAttributes ra, Artist artist) {
        artistService.add(artist);
        ra.addFlashAttribute("newArtist", artist);
        return "redirect:/admin/artist";
    }

}
