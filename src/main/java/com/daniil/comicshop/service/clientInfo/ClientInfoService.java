package com.daniil.comicshop.service.clientInfo;

import com.daniil.comicshop.entity.ClientInfo;

import java.util.List;
import java.util.Optional;

public interface ClientInfoService {
    ClientInfo add(ClientInfo info);
    Optional<ClientInfo> getById(int id);
    List<ClientInfo> getAll();
    ClientInfo change(ClientInfo info);
    Optional<ClientInfo> delete(int id);
}
