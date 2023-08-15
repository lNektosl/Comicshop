package com.daniil.comicshop.controler.admin;

import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.service.SeriesService;
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
@RequestMapping("/admin/series")
@RequiredArgsConstructor
public class AdminSeriesController {
    private final SeriesService seriesService;

    @GetMapping
    public String getSeriess(Model model) {
        model.addAttribute("seriess", seriesService.getAll());
        return "admin/series/series-page";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable Integer id, Model model) {
        Optional<Series> series = seriesService.getById(id);
        if (series.isPresent()) {
            model.addAttribute("series", series.get());
            return "admin/series/series";
        }
        return "redirect:/admin/series";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        ra.addFlashAttribute("deletedSeries", seriesService.delete(id).get());
        return "redirect:/admin/series";
    }

    @GetMapping("{id}/change")
    public String change(@PathVariable Integer id, Model model) {
        Optional<Series> series = seriesService.getById(id);
        if (series.isPresent()) {
            model.addAttribute("series", series.get());
            return "admin/series/series-changeForm";
        }
        return "redirect:/admin/series";
    }

    @PostMapping("{id}/change")
    public String change(RedirectAttributes ra, Series series) {
        seriesService.change(series);
        ra.addFlashAttribute("updatedSeries", seriesService.change(series));
        return "redirect:/admin/series";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("series", new Series());
        return "admin/series/series-form";
    }

    @PostMapping("add")
    public String add(RedirectAttributes ra, Series series) {
        seriesService.add(series);
        ra.addFlashAttribute("newSeries", series);
        return "redirect:/admin/series";
    }

}
