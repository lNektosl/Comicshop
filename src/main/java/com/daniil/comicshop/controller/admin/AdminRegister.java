package com.daniil.comicshop.controller.admin;

import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/register")
public class AdminRegister {
    private final ClientService clientService;

    @GetMapping
    public String register(Model model) {
        model.addAttribute("client", new Client());
        return "admin/register/register";
    }

    @PostMapping
    public String register(Client client, RedirectAttributes ra) {
        if (!clientService.checkCreds(client)) {
            client.setRole("ADMIN");
            clientService.add(client);
            return "redirect:/admin/menu";
        }
        ra.addFlashAttribute("error", true);
        return "redirect:/admin/register";
    }
}
