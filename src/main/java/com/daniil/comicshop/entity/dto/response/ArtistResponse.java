package com.daniil.comicshop.entity.dto.response;

import java.util.List;

public record ArtistResponse(int id,
                             String name,
                             List<Integer> comics) {
}
