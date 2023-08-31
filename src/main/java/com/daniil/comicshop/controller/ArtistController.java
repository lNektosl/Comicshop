package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.artist.ArtistService;
import com.daniil.comicshop.service.client.ClientService;
import com.daniil.comicshop.service.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
    private final ClientService clientService;
    private final ComicService comicService;

    @GetMapping("{id}")
    public String getArtistPage(@PathVariable Integer id, Model model,
                                @RequestParam(required = false, defaultValue = "0") Integer pageNum) {

        Optional<Artist> artist = artistService.getById(id);
        if (artist.isPresent()) {
            Pageable pageable = PageRequest.of(pageNum,9);
            Page<Comic> page = comicService.getPageByArtist(artist.get(),pageable);

            model.addAttribute("artist", artist.get());
            model.addAttribute("comics",page.getContent());
            model.addAttribute("currentPage",pageNum);
            model.addAttribute("totalPages",page.getTotalPages());

            String currentClientLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<Client> client = clientService.getByLogin(currentClientLogin);
            boolean isFavorite = false;
            if (client.isPresent()) {
                isFavorite = client.get().getArtists().contains(artist.get());
            }
            model.addAttribute("favorite", isFavorite);
            return "info/artist";
        }
        return "redirect:/cabinet/artists";
    }

    @GetMapping("{id}/toggle")
    public String artistChangeState(@PathVariable Integer id, RedirectAttributes ra) {
        Optional<Artist> artist = artistService.getById(id);
        if (artist.isPresent()) {
            String currentClientLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<Client> client = clientService.getByLogin(currentClientLogin);
            if (client.isPresent()) {
                boolean isFavorite = clientService.toggleArtist(client.get(), artist.get());
                ra.addFlashAttribute("favorite", isFavorite);
                return "redirect:/artist/{id}";
            }
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
