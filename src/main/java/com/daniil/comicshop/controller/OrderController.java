package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.entity.Order;
import com.daniil.comicshop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String getCurrentOrder(Model model, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping("/add")
    public String addItem(RedirectAttributes ra, CartItem cartItem, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        cartItem.setOrder(order);
        if (order.getComics() == null) {
            order.setComics(new ArrayList<>());
        }
        order.getComics().add(cartItem);
        session.setAttribute("order", order);
        return "redirect:/order";
    }
}
