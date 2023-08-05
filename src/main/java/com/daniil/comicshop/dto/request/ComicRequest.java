package com.daniil.comicshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ComicRequest {
    private String name;
    private int amount;
    private MultipartFile image;
    private List<Integer> artistIds;
    private List<Integer> authorIds;
    private int publisherId;
    private int seriesId;
    private String description;
    private Double price;
}
