package com.daniil.comicshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    private UUID uuid;
    @Column(name = "client_name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "h_password")
    private String password;

    @OneToMany
    private List<Author> authors;

    @OneToMany
    private List<Artist> artists;

    @OneToMany
    private List<Series> series;
}
