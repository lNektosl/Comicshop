package com.daniil.comicshop.dto.response;

import java.util.List;

public record ArtistResponse(int id,
                             String name,
                             List<Integer> comics) {
}
