package com.lrtech.lrbackend.mappers;

import com.lrtech.lrbackend.dtoModels.FilamentDTO;
import com.lrtech.lrbackend.models.Filament;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {FilamentColorMapper.class})
public interface FilamentMapper {

    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    Filament filamentDtoToFilament(FilamentDTO filamentDTO);

    FilamentDTO filamentToFilamentDto(Filament filament);
}
