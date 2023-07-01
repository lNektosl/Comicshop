package com.daniil.comicshop.mapper;

import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.dto.request.ArtistRequest;
import com.daniil.comicshop.entity.dto.response.ArtistResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    Artist artistRequestToArtist(ArtistRequest artistRequest);
    @Mapping(source = "id",target = "id")
    @Mapping(source = "comics", target = "comics",qualifiedByName = "mapComics")
    ArtistResponse artistToArtistResponse(Artist artist);

    @Named("mapComics")
    default Integer mapComics(Comic comic){return comic.getId();}
}
