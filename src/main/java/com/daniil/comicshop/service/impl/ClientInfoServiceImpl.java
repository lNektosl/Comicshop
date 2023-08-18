package com.daniil.comicshop.service.impl;

import com.daniil.comicshop.entity.ClientInfo;
import com.daniil.comicshop.repository.ClientInfoRepository;
import com.daniil.comicshop.service.ClientInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientInfoServiceImpl implements ClientInfoService {
    private final ClientInfoRepository repository;
    @Override
    public ClientInfo add(ClientInfo info) {
        return repository.save(info);
    }

    @Override
    public Optional<ClientInfo> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<ClientInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public ClientInfo change(ClientInfo info) {
        if (repository.findById(info.getId()).isPresent()){
            return repository.save(info);}
        throw new NoSuchElementException();
    }

    @Override
    public Optional<ClientInfo> delete(int id) {
        Optional<ClientInfo> info = getById(id);
        repository.deleteById(id);
        return info;
    }
}
