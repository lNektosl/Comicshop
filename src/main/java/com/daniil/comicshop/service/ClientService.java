package com.daniil.comicshop.service;


import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Series;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<Client> getById(UUID id);
    List<Client> getAll();
    Client add(Client client);
    Client change(Client client);
    Optional<Client> delete(UUID id);
    Client addSeries(UUID id, Series series);
    Client addAuthor(UUID id, Author author);
    Client addArtist(UUID id, Artist artist);
}
