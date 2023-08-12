package com.daniil.comicshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "address")
    private String address;
    @Column(name ="phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "uuid")
    private Client client;

    @ManyToMany
    @JoinTable( name = "cart_item",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn (name = "comic_id")}
    )
    private List<Comic> comics;
    @Column(name = "date")
    private LocalDate date;

}
