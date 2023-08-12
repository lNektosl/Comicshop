package com.daniil.comicshop.repository;

import com.daniil.comicshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Integer> {
}
