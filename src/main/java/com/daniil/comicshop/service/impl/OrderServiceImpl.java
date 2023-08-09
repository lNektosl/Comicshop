package com.daniil.comicshop.service.impl;


import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Order;
import com.daniil.comicshop.repository.OrderRepository;
import com.daniil.comicshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Override
    public Order add(Order order) {
        order.setUuid(UUID.randomUUID());
        return repository.save(order);
    }

    @Override
    public Optional<Order> getById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order change(Order order) {
        if (repository.findById(order.getUuid()).isPresent()){
            return repository.save(order);}
        throw new NoSuchElementException();
    }

    @Override
    public Optional<Order> delete(UUID uuid) {
        Optional<Order> order = repository.findById(uuid);
        repository.deleteById(uuid);
        return order;
    }
}
