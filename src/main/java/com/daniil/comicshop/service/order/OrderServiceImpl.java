package com.daniil.comicshop.service.order;

import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.ClientInfo;
import com.daniil.comicshop.entity.Order;
import com.daniil.comicshop.repository.ClientInfoRepository;
import com.daniil.comicshop.repository.ClientRepository;
import com.daniil.comicshop.repository.OrderRepository;
import com.daniil.comicshop.service.client.ClientService;
import com.daniil.comicshop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final ClientInfoRepository infoRepository;
    private final ClientRepository clientRepository;

    @Override
    public Order add(ClientInfo info, Order order) {
        info = getInfo(info, order);
        order.setInfo(info);
        return repository.save(order);
    }

    @Override
    public Order addWithClient(ClientInfo info, Order order, Client client) {
        info = getInfo(info, order);
        order.setClient(client);
        order.setInfo(info);
        repository.save(order);
        if (client.getInfo() == null) {
            client.setInfo(info);
            clientRepository.save(client);
        }
        return order;
    }

    private ClientInfo getInfo(ClientInfo info, Order order) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("title")
                .withIgnoreCase();
        if (!infoRepository.exists(Example.of(info, matcher))) {
            info = infoRepository.save(info);
        } else {
            info = infoRepository.findOne(Example.of(info, matcher)).orElseThrow();
        }
        order.setDate(LocalDate.now());
        return info;
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
