package com.daniil.comicshop.service;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order add(Order order, Order attribute);
    Optional<Order> getById(Integer id);
    List<Order> getAll();
    Order change(Order order);
    Optional<Order> delete(Integer id);
    Order addItem(Order order, CartItem item);
}
