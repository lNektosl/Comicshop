package com.daniil.comicshop.mapper;

import com.daniil.comicshop.entity.Comic;
import com.daniil.comicshop.entity.Publisher;
import com.daniil.comicshop.entity.dto.request.ModelRequest;
import com.daniil.comicshop.entity.dto.response.ModelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    Publisher modelRequestToPublisher(ModelRequest modelRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "comics", target = "comics", qualifiedByName = "mapComics")
    ModelResponse publisherToModelResponse(Publisher publisher);

    @Named("mapComics")
    default List<Integer> mapComics(Set<Comic> comics) {
        return comics.stream()
                .map(Comic::getId)
                .toList();
    }
}
