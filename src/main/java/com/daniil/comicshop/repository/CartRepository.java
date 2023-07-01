package com.daniil.comicshop.repository;


import com.daniil.comicshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
