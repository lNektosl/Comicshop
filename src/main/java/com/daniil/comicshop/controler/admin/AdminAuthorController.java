package com.daniil.comicshop.controler.admin;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin/author")
@RequiredArgsConstructor
public class AdminAuthorController {
    private final AuthorService authorService;

    @GetMapping
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "admin/author/author-page";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable Integer id, Model model) {
        Optional<Author> author = authorService.getById(id);
        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            return "admin/author/author";
        }
        return "redirect:/admin/author";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        ra.addFlashAttribute("deletedAuthor", authorService.delete(id).get());
        return "redirect:/admin/author";
    }

    @GetMapping("{id}/change")
    public String change(@PathVariable Integer id, Model model) {
        Optional<Author> author = authorService.getById(id);
        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            return "admin/author/author-changeForm";
        }
        return "redirect:/admin/author";
    }

    @PostMapping("{id}/change")
    public String change(RedirectAttributes ra, Author author) {
        authorService.change(author);
        ra.addFlashAttribute("updatedAuthor", authorService.change(author));
        return "redirect:/admin/author";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("author", new Author());
        return "admin/author/author-form";
    }

    @PostMapping("add")
    public String add(RedirectAttributes ra, Author author) {
        authorService.add(author);
        ra.addFlashAttribute("newAuthor", author);
        return "redirect:/admin/author";
    }

}
