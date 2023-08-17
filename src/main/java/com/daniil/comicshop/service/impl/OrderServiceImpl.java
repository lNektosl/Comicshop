package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.entity.Order;
import com.daniil.comicshop.repository.OrderRepository;
import com.daniil.comicshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Override
    public Order add(Order order, Order attribute) {
        attribute.setDate(LocalDate.now());
        attribute.setAddress(order.getAddress());
        attribute.setPhone(order.getPhone());
        return repository.save(attribute);
    }

    @Override
    public Optional<Order> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public Order change(Order order) {
        if (repository.findById(order.getId()).isPresent()) {
            return repository.save(order);
        }
        throw new NoSuchElementException();
    }

    @Override
    public Optional<Order> delete(Integer id) {
        Optional<Order> order = repository.findById(id);
        repository.deleteById(id);
        return order;
    }

    @Override
    public Order addItem(Order order, CartItem item) {
        if (order.getComics().contains(item)) {
            order.getComics().get(order.getComics().indexOf(item))
                    .add(item.getAmount());
        } else {
            order.getComics().add(item);
        }
        return order;
    }
}
