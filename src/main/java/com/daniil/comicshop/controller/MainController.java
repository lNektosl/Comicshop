package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.service.client.ClientService;
import com.daniil.comicshop.service.comic.ComicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ClientService clientService;
    private final ComicService comicService;

    @GetMapping("/")
    public String getMainPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer pageNum) {
        Page<Comic> page = comicService.getPage(pageNum);
        model.addAttribute("comics", page.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", page.getTotalPages());
        return "index";
    }

    @GetMapping("/admin")
    public String adminMenu() {
        return "admin/admin-menu";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("client", new Client());
        return "register";
    }

    @PostMapping("/register")
    public String register(Client client, RedirectAttributes ra) {
        if (!clientService.checkCreds(client)) {
            clientService.add(client);
            return "redirect:/login";
        }
        ra.addFlashAttribute("error", true);
        return "redirect:/register";
    }
}
