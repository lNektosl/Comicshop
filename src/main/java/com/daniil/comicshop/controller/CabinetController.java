package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.ClientInfo;
import com.daniil.comicshop.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cabinet")
@RequiredArgsConstructor
public class CabinetController {
    private final ClientService clientService;

    @GetMapping
    public String getCabinetPage(Model model) {
        Client client = clientService.getByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow();

        model.addAttribute("client", client);

        return "cabinet/cabinet";
    }

    @GetMapping("authors")
    public String getAuthors(Model model){
        Client client = clientService.getByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow();
        model.addAttribute("authors",client.getAuthors());
        return "cabinet/author-list";
    }

    @GetMapping("artists")
    public String getArtists(Model model){
        Client client = clientService.getByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow();
        model.addAttribute("artists",client.getArtists());
        return "cabinet/artist-list";
    }
    @GetMapping("series")
    public String getSeries(Model model){
        Client client = clientService.getByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow();
        model.addAttribute("series",client.getSeries());
        return "cabinet/series-list";
    }
    @GetMapping("orders")
    public String getOrders(Model model){
        Client client = clientService.getByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow();
        model.addAttribute("orders",client.getOrders());
        return "cabinet/order-list";
    }
    @GetMapping("info")
    public String clientInfo(Model model){
        Client client = clientService.getByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow();
        model.addAttribute("info",client.getInfo());
        return "cabinet/clientInfo-form";
    }
    @PostMapping("info")
    public String clientInfo(RedirectAttributes ra, ClientInfo clientInfo){
        Client client = clientService.getByLogin(SecurityContextHolder
                .getContext().getAuthentication().getName()).orElseThrow();
        clientService.changeInfo(client,clientInfo);
        ra.addFlashAttribute("changedInfo",clientInfo);
        return "redirect:info";
    }
}
