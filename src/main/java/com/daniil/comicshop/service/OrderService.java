package com.daniil.comicshop.service;

import com.daniil.comicshop.dto.request.ComicIdsRequest;
import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order add(Order order);
    Optional<Order> getById(UUID uuid);
    List<Order> getAll();
    Artist change(Order order);
    Optional<Order> delete(UUID uuid);
}
