package com.lrtech.lrbackend.dtoModels;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderFilamentDTO {
    private UUID filamentId;

    private String type;

    private String description;

    private UUID colorId;

    private String name;

    private Integer availableGrams;
}
