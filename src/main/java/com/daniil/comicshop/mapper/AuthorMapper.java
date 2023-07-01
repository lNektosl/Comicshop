package com.daniil.comicshop.mapper;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.dto.request.ModelRequest;
import com.daniil.comicshop.entity.dto.response.ModelResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author modelRequestToAuthor(ModelRequest modelRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "comics", target = "comics", qualifiedByName = "mapComics")
    ModelResponse authorToModelResponse(Author author);

    @Named("mapComics")
    default Integer mapComics(Comic comic) {return comic.getId();
    }
}
