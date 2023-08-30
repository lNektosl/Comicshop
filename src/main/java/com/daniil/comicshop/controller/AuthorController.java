package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.service.author.AuthorService;
import com.daniil.comicshop.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ClientService clientService;

    @GetMapping("{id}")
    public String getAuthorPage(@PathVariable Integer id, Model model) {
        Optional<Author> author = authorService.getById(id);
        if (author.isPresent()) {
            model.addAttribute("author", author.get());
            String currentClientLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<Client> client = clientService.getByLogin(currentClientLogin);
            boolean isFavorite = false;
            if (client.isPresent()) {
                isFavorite = client.get().getAuthors().contains(author.get());
            }
            model.addAttribute("favorite", isFavorite);
            return "info/author";
        }
        return "redirect:/cabinet/authors";
    }

    @GetMapping("{id}/toggle")
    public String authorChangeState(@PathVariable Integer id, RedirectAttributes ra) {
        Optional<Author> author = authorService.getById(id);
        if (author.isPresent()) {
            String currentClientLogin = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<Client> client = clientService.getByLogin(currentClientLogin);
            if (client.isPresent()) {
                boolean isFavorite = clientService.toggleAuthor(client.get(), author.get());
                ra.addFlashAttribute("favorite", isFavorite);
                return "redirect:/author/{id}";
            }
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
