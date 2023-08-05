package com.daniil.comicshop.dto.response;

import java.util.Set;

public record PublisherResponse(int id,
                                String name,
                                Set<Integer> comics) {
}
