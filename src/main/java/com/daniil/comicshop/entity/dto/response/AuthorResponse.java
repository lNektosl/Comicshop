package com.daniil.comicshop.entity.dto.response;

import java.util.Set;

public record AuthorResponse(int id,
                             String name,
                             Set<Integer> comics) {
}
