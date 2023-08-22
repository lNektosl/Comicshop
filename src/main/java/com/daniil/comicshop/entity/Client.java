package com.daniil.comicshop.entity;

import jakarta.persistence.*;
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
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "h_password", nullable = false)
    @ToString.Exclude
    private String password;
    @Column(name = "role")
    private String role;

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

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "client_info_id", referencedColumnName = "id")
    private ClientInfo info;

    @PrePersist
    public void defaultRole() {
        if (this.role == null || this.role.isEmpty()) {
            this.role = "USER";
        }
    }
}
