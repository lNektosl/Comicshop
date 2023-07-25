package com.daniil.comicshop.mapper;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.dto.request.AuthorRequest;
import com.daniil.comicshop.entity.dto.request.PublisherRequest;
import com.daniil.comicshop.entity.dto.response.PublisherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher publisherRequestToPublisher(PublisherRequest modelRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "comics", target = "comics", qualifiedByName = "mapComics")
    PublisherResponse publisherToPublisherResponse(Publisher publisher);

    @Named("mapComics")
    default Set<Integer> mapComics(Set<Comic> comics) {
        return comics.stream()
                .map(Comic::getId)
                .collect(Collectors.toSet());
    }
}
