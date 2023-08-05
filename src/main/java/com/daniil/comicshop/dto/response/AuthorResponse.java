package com.daniil.comicshop.dto.response;

import java.util.Set;

public record AuthorResponse(int id,
                             String name,
                             Set<Integer> comics) {
}
