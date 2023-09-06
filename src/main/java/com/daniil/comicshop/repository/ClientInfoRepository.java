package com.daniil.comicshop.repository;

import com.daniil.comicshop.entity.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInfoRepository extends JpaRepository<ClientInfo,Integer> {
}
