package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.dto.CartItemRequest;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.ClientInfo;
import com.daniil.comicshop.entity.Order;
import com.daniil.comicshop.service.client.ClientService;
import com.daniil.comicshop.service.order.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ClientService clientService;

    @GetMapping
    public String getCurrentOrder(Model model, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);
        return "order/order";
    }

    @PostMapping("/add")
    public String addItem(CartItem cartItem, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        cartItem.setOrder(order);
        if (order.getComics() == null) {
            order.setComics(new ArrayList<>());
        }
        order = orderService.addItem(order, cartItem);
        session.setAttribute("order", order);
        return "redirect:/order";
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmOrder(HttpSession session,
                                               @RequestBody List<CartItemRequest> items) {
        BigDecimal total;
        Order order = (Order) session.getAttribute("order");
        order.getComics().forEach(item -> items.stream()
                .filter(request -> item.getComic().getId() == request.comicId())
                .findFirst()
                .ifPresent(request -> item.setAmount(request.amount())));

        total= order.getComics().stream()
                .map(item -> BigDecimal.valueOf(item.getAmount()).multiply(item.getComic().getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);
        session.setAttribute("order", order);
        return ResponseEntity.ok("Data received successfully");
    }

    @GetMapping("/save")
    public String saveOrder(Model model, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (order.getComics().isEmpty()) {
            return "redirect:/";
        }
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.getByLogin(name).orElseThrow();
            if (client.getInfo() != null) {
                model.addAttribute("info", client.getInfo());
                return "order/add-form";
            }
        }
        model.addAttribute("info", new ClientInfo());
        return "order/add-form";
    }

    @PostMapping("/save")
    public String saveOrder(ClientInfo info, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order order = (Order) session.getAttribute("order");
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            Client client = clientService.getByLogin(name).orElseThrow();
            orderService.addWithClient(info,order,client);
            session.setAttribute("order", new Order());
            return "redirect:/";
        }
        orderService.add(info, order);
        session.setAttribute("order", new Order());
        return "redirect:/";
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam Integer comicId, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        order.getComics().stream()
                .filter(item -> item.getComic().getId() == comicId)
                .findFirst()
                .ifPresent(itemToRemove -> order.getComics().remove(itemToRemove));
        return ResponseEntity.ok("Comic was removed");
    }
}