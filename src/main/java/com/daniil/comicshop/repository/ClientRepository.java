package com.daniil.comicshop.repository;

import com.daniil.comicshop.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
public interface ClientRepository extends JpaRepository<Client, UUID> {
}
