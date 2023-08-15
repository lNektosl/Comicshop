package com.daniil.comicshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    @ToString.Exclude
    private Set<Comic> comics;

    @ManyToMany(mappedBy = "series")
    @JsonIgnore
    @ToString.Exclude
    private List<Client> clients;

    public void removeComic(Comic comic) {
        comics.remove(comic);
        comic.setSeries(null);
    }
}
