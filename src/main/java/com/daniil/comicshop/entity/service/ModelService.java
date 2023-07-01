package com.daniil.comicshop.entity.service;

import com.daniil.comicshop.entity.dto.response.ModelResponse;

public interface ModelService<T> {
    ModelResponse getById(int id);
}
