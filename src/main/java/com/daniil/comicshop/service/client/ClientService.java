package com.daniil.comicshop.service.client;


import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.ClientInfo;
import com.daniil.comicshop.entity.Series;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    Optional<Client> getById(UUID id);
    Optional<Client> getByLogin(String login);
    Boolean checkCreds(Client client);
    List<Client> getAll();
    Client add(Client client);
    Client change(Client client);
    Optional<Client> delete(UUID id);
    Client changeInfo(Client client, ClientInfo info);
    Boolean toggleAuthor(Client client,Author author);
    Boolean toggleArtist(Client client,Artist artist);
    Boolean toggleSeries(Client client,Series series);
}
