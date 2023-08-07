package com.daniil.comicshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "clients")
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
    @ToString.Exclude
    private String password;

    @ManyToMany
    @JoinTable(
            name = "author_client",
            joinColumns = {@JoinColumn(name = "client_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;

    @ManyToMany
    @JoinTable(
            name = "artist_client",
            joinColumns = {@JoinColumn(name = "client_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")}
    )
    private List<Artist> artists;

    @ManyToMany
    @JoinTable(
            name = "series_client",
            joinColumns = {@JoinColumn(name = "client_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "series_id")}
    )
    private List<Series> series;
}
