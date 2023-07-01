package com.daniil.comicshop.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "artists")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Cascade(CascadeType.ALL)
    private int id;

    @Column(name = "artist_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "artist_comic",
            joinColumns = {@JoinColumn(name = "artist_id")},
            inverseJoinColumns = {@JoinColumn(name = "comic_id")}
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<Comic> comics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return name.equals(artist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
