package com.daniil.comicshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "comics")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comic_name")
    private String name;

    @Column(name = "image")
    private String imagePath;

    @PrePersist
    public void defaultImgPath(){
        if(this.imagePath==null||this.imagePath.isEmpty()){
            this.imagePath = "/images/1.jpg";}
    }
    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private int amount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "artist_comic",
            joinColumns = {@JoinColumn(name = "comic_id")},
            inverseJoinColumns = {@JoinColumn(name = "artist_id")}
    )
    private List<Artist> artists;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "author_comic",
            joinColumns = {@JoinColumn(name = "comic_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id",referencedColumnName = "id")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "series_id",referencedColumnName = "id")
    private Series series;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "adding_date")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comic comic = (Comic) o;
        return id == comic.id && name.equals(comic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
