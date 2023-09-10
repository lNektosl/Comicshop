package com.daniil.comicshop.mapper;

import com.daniil.comicshop.dto.CartItemRequest;
import com.daniil.comicshop.entity.CartItem;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.repository.ComicRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CartItemMapper {

    @Autowired
    private ComicRepository comicRepository;

    @Mapping(source = "comicId", target = "comic")
    public abstract CartItem toCartItem(CartItemRequest cartItemRequest);

    protected Comic mapComicIdToComic(Integer comicId) {
        return comicRepository.findById(comicId).orElse(null);
    }
}
