package com.daniil.comicshop.controller;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.dto.CartItemRequest;
import com.daniil.comicshop.entity.ClientInfo;
import com.daniil.comicshop.entity.Order;
import com.daniil.comicshop.service.order.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

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
            System.out.println("cart created");
        }
        order = orderService.addItem(order, cartItem);
        session.setAttribute("order", order);
        return "redirect:/order";
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmOrder(HttpSession session,
                                               @RequestBody List<CartItemRequest> items) {
        Order order = (Order) session.getAttribute("order");
        order.getComics().forEach(item -> {
            items.stream()
                    .filter(request -> item.getComic().getId() == request.comicId())
                    .findFirst()
                    .ifPresent(request -> item.setAmount(request.amount()));
        });
        session.setAttribute("order",order);
        return ResponseEntity.ok("Data received successfully");
    }

    @GetMapping("/save")
    public String saveOrder(Model model) {
        model.addAttribute("info", new ClientInfo());
        return "order/add-form";
    }

    @PostMapping("/save")
    public String saveOrder(ClientInfo info, HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        orderService.add(info, order);
        order.getComics().clear();
        session.setAttribute("order",order);
        return "redirect:/";
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> delete(@RequestParam Integer comicId,HttpSession session){
        Order order = (Order) session.getAttribute("order");
        order.getComics().stream()
                .filter(item -> item.getComic().getId() == comicId)
                .findFirst()
                .ifPresent(itemToRemove -> order.getComics().remove(itemToRemove));
        return ResponseEntity.ok("Comic was removed");
    }
}
