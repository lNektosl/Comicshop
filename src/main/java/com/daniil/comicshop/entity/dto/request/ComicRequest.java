package com.daniil.comicshop.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ComicRequest {
    private String name;
    private int amount;
    private List<Integer> artistIds;
    private List<Integer> authorIds;
    private int publisherId;
    private int seriesId;
}
