package com.daniil.comicshop.entity;

import com.daniil.comicshop.entity.Comic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private Integer id;

    @Column(name = "publisher_name")
    private String name;

    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL,orphanRemoval = false)
    @JsonIgnore
    @ToString.Exclude
    private Set<Comic> comics;

    public void removeComic(Comic comic) {
        comics.remove(comic);
        comic.setPublisher(null);
    }
}
