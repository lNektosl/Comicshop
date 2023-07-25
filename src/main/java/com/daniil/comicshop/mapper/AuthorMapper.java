package com.daniil.comicshop.mapper;

import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;

import com.daniil.comicshop.entity.dto.request.AuthorRequest;
import com.daniil.comicshop.entity.dto.response.AuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author authorRequestToAuthor(AuthorRequest authorRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "comics", target = "comics", qualifiedByName = "mapComics")
    AuthorResponse authorToAuthorResponse(Author author);

    @Named("mapComics")
    default Integer mapComics(Comic comic) {return comic.getId();
    }
}
