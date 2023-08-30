package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.author.AuthorService;
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
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final ClientService clientService;
    private final ComicService comicService;

    @GetMapping("{id}")
    public String getAuthorPage(@PathVariable Integer id, Model model,
                                @RequestParam(required = false, defaultValue = "0") Integer pageNum) {

        Optional<Author> author = authorService.getById(id);
        if (author.isPresent()) {
            Pageable pageable = PageRequest.of(pageNum,9);
            Page<Comic> page = comicService.getPageByAuthor(author.get(),pageable);

            model.addAttribute("author", author.get());
            model.addAttribute("comics",page.getContent());
            model.addAttribute("currentPage",pageNum);
            model.addAttribute("totalPages",page.getTotalPages());

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
