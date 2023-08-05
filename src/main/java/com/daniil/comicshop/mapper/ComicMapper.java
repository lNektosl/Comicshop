package com.daniil.comicshop.mapper;


import com.daniil.comicshop.entity.Artist;
import com.daniil.comicshop.entity.Author;
import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.dto.request.ComicRequest;
import com.daniil.comicshop.dto.response.ComicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface ComicMapper {

    Comic comicRequestToComic(ComicRequest comicRequest);

    @Mapping(source = "publisher.id",target = "publisherId")
    @Mapping(source = "series.id",target = "seriesId")
    @Mapping(source = "artists",target = "artistIds",qualifiedByName = "mapArtistId")
    @Mapping(source = "authors",target = "authorIds",qualifiedByName = "mapAuthorId")
    @Mapping(source = "price", target = "price")
    ComicResponse comicToComicResponse(Comic comic);

    @Named("mapArtistId")
    default Integer mapArtistId(Artist artist){
        return artist.getId();
    }

    @Named("mapAuthorId")
    default Integer mapAuthorId(Author author){
        return author.getId();
    }
}
