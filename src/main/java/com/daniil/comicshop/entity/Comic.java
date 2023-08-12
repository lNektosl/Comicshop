package com.daniil.comicshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
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
    @Cascade(CascadeType.ALL)
    private int id;

    @Column(name = "comic_name")
    private String name;

    @Column(name = "image")
    private String imagePath;

    @PrePersist
    public void defaultImgPath(){
        if(this.imagePath==null||this.imagePath.isEmpty())
            this.imagePath = "/images/1.jpg";
    }
    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private int amount;

    @ManyToMany(mappedBy = "comics")
    @Cascade(CascadeType.MERGE)
    private List<Artist> artists;

    @ManyToMany(mappedBy = "comics")
    @JsonIgnore
    @Cascade(CascadeType.MERGE)
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name = "publisher_id",referencedColumnName = "id")
    @JsonIgnore
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "series_id",referencedColumnName = "id")
    private Series series;

    @Column(name = "price")
    private BigDecimal price;


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
