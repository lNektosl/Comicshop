package com.daniil.comicshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "cart_item")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "comic_id",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private Comic comic;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private Order order;

    @Column(name = "amount")
    private Integer amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return comic.equals(cartItem.comic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comic);
    }
    public void add(Integer amount){
        this.amount += amount;
    }
}
