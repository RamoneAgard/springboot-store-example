package com.lrtech.lrbackend.mappers;

import com.lrtech.lrbackend.dtoModels.FilamentColorDTO;
import com.lrtech.lrbackend.models.FilamentColor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FilamentColorMapper {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    FilamentColor filamentColorDtoToFilamentColor(FilamentColorDTO filamentColorDTO);

    @Mapping(target = "filament", ignore = true)
    @Mapping(target = "filamentType", source = "filament.type")
    FilamentColorDTO filamentColorToFilamentColorDto(FilamentColor filamentColor);
}
