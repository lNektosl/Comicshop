package com.daniil.comicshop.entity.dto.response;

import java.util.List;

public record ModelResponse(int id,
                            String name,
                            List<Integer> comics) {
}
