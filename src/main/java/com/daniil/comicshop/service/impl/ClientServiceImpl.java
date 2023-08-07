package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Client;
import com.daniil.comicshop.entity.Series;
import com.daniil.comicshop.repository.ClientRepository;
import com.daniil.comicshop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public Optional<Client> getById(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client add(Client client) {
        client.setUuid(UUID.nameUUIDFromBytes(client.getEmail().getBytes()));
        client.setPassword(BCrypt.hashpw(client.getPassword(),BCrypt.gensalt()));
        return clientRepository.save(client);
    }

    @Override
    public Client change(Client client) {
        if (clientRepository.findById(client.getUuid()).isPresent()){
        return clientRepository.save(client);}
        throw new NoSuchElementException();
    }

    @Override
    public Optional<Client> delete(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        clientRepository.deleteById(id);
        return client;
    }

    @Override
    public Client addSeries(UUID id, Series series) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            client.get().getSeries().add(series);
            return clientRepository.save(client.get());}
        throw new NoSuchElementException();
    }

    @Override
    public Client addAuthor(UUID id, Author author) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            client.get().getAuthors().add(author);
            return clientRepository.save(client.get());}
        throw new NoSuchElementException();
    }

    @Override
    public Client addArtist(UUID id, Artist artist) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            client.get().getArtists().add(artist);
            return clientRepository.save(client.get());}
        throw new NoSuchElementException();
    }

}
