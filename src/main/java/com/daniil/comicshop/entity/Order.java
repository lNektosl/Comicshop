package com.daniil.comicshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
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
    @JsonIgnore
    @ToString.Exclude
    private Client client;

    @OneToMany(mappedBy = "order")
    private List<CartItem> comics;
    @Column(name = "date")
    private LocalDate date;

}
