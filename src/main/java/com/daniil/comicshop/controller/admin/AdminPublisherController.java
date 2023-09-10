package com.daniil.comicshop.controller.admin;

import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.service.publisher.PublisherService;
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
@RequestMapping("/admin/publisher")
@RequiredArgsConstructor
public class AdminPublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public String getPublishers(Model model) {
        model.addAttribute("publishers", publisherService.getAll());
        return "admin/publisher/publisher-page";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable Integer id, Model model) {
        Optional<Publisher> publisher = publisherService.getById(id);
        if (publisher.isPresent()) {
            model.addAttribute("publisher", publisher.get());
            return "admin/publisher/publisher";
        }
        return "redirect:/admin/publisher";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        ra.addFlashAttribute("deletedPublisher", publisherService.delete(id).get());
        return "redirect:/admin/publisher";
    }

    @GetMapping("{id}/change")
    public String change(@PathVariable Integer id, Model model) {
        Optional<Publisher> publisher = publisherService.getById(id);
        if (publisher.isPresent()) {
            model.addAttribute("publisher", publisher.get());
            return "admin/publisher/publisher-changeForm";
        }
        return "redirect:/admin/publisher";
    }

    @PostMapping("{id}/change")
    public String change(RedirectAttributes ra, Publisher publisher) {
        publisherService.change(publisher);
        ra.addFlashAttribute("updatedPublisher", publisherService.change(publisher));
        return "redirect:/admin/publisher";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "admin/publisher/publisher-form";
    }

    @PostMapping("add")
    public String add(RedirectAttributes ra, Publisher publisher) {
        publisherService.add(publisher);
        ra.addFlashAttribute("newPublisher", publisher);
        return "redirect:/admin/publisher";
    }

}
