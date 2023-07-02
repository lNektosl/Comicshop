package com.daniil.comicshop.service;

import com.daniil.comicshop.entity.dto.response.ModelResponse;

public interface ModelService<T> {
    ModelResponse getById(int id);
}
