package com.daniil.comicshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @Column(name = "id")
    private UUID id;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

}
