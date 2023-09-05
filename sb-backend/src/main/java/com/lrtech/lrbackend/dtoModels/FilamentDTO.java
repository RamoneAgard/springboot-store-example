package com.lrtech.lrbackend.dtoModels;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class FilamentDTO {

    private UUID id;

    @NotBlank
    private String type;

    @Builder.Default
    @Valid
    private Set<FilamentColorDTO> colors = new HashSet<>();

    @NotBlank
    private String description;
}
