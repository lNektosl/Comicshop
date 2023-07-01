package com.daniil.comicshop.entity;

import com.daniil.comicshop.entity.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "publishers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "publisher_name")
    private String name;

    @OneToMany(mappedBy = "publisher")
//    @JsonIgnore
//    @ToString.Exclude
    private List<Comic> comics;
}
