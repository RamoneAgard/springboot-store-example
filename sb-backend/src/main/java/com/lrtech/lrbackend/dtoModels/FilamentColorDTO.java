package com.lrtech.lrbackend.dtoModels;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FilamentColorDTO {

    private UUID id;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    private Integer availableGrams;

    @JsonIgnore
    private FilamentDTO filament;

    @NotNull
    private String filamentType;
}
