package com.daniil.comicshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "uuid")
    @JsonIgnore
    @ToString.Exclude
    private Client client;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<CartItem> comics;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_info_id",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private ClientInfo info;

    @Column(name="total")
    private BigDecimal total;
}
