package com.daniil.comicshop.service.order;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.ClientInfo;
import com.daniil.comicshop.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order add(ClientInfo info, Order order);
    Order addWithClient(ClientInfo info, Order order, Client client);
    Optional<Order> getById(Integer id);
    List<Order> getAll();
    Order change(Order order);
    Optional<Order> delete(Integer id);
    Order addItem(Order order, CartItem item);
}
