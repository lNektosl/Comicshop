package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.series.SeriesService;
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
@RequestMapping("/series")
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;
    private final ClientService clientService;
    private final ComicService comicService;

    @GetMapping("{id}")
    public String getSeriesPage(@PathVariable Integer id, Model model,
                                @RequestParam(required = false, defaultValue = "0") Integer pageNum) {

        Optional<Series> series = seriesService.getById(id);
        if (series.isPresent()) {
            Pageable pageable = PageRequest.of(pageNum,9);
            Page<Comic> page = comicService.getPageBySeries(series.get(),pageable);

            model.addAttribute("series", series.get());
            model.addAttribute("comics",page.getContent());
            model.addAttribute("currentPage",pageNum);
            model.addAttribute("totalPages",page.getTotalPages());

            String currentClientLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<Client> client = clientService.getByLogin(currentClientLogin);
            boolean isFavorite = false;
            if (client.isPresent()) {
                isFavorite = client.get().getSeries().contains(series.get());
            }
            model.addAttribute("favorite", isFavorite);
            return "info/series";
        }
        return "redirect:/cabinet/series";
    }

    @GetMapping("{id}/toggle")
    public String seriesChangeState(@PathVariable Integer id, RedirectAttributes ra) {
        Optional<Series> series = seriesService.getById(id);
        if (series.isPresent()) {
            String currentClientLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<Client> client = clientService.getByLogin(currentClientLogin);
            if (client.isPresent()) {
                boolean isFavorite = clientService.toggleSeries(client.get(), series.get());
                ra.addFlashAttribute("favorite", isFavorite);
                return "redirect:/series/{id}";
            }
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
