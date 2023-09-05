package com.lrtech.lrbackend.mappers;

import com.lrtech.lrbackend.dtoModels.OrderFilamentDTO;
import com.lrtech.lrbackend.models.FilamentColor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderFilamentMapper {

    @Mapping(target = "colorId", source = "id")
    @Mapping(target = "filamentId", source = "filament.id")
    @Mapping(target = "description", source = "filament.description")
    @Mapping(target = "type", source = "filament.type")
    OrderFilamentDTO filamentColorToOrderFilamentDto(FilamentColor filamentColor);

}
