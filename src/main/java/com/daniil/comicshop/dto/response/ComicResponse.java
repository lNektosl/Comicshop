package com.daniil.comicshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComicResponse {
    private int id;
    private String name;
    private int amount;
    private List<Integer> authorIds;
    private List<Integer> artistIds;
    private int publisherId;
    private int seriesId;
    private String imagePath;
    private String description;
    private Double price;
}
