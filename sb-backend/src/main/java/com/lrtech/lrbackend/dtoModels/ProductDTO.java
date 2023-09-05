package com.lrtech.lrbackend.dtoModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class ProductDTO {

    private UUID id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String productName;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @Builder.Default
    @NotNull
    private Set<FilamentDTO> filaments = new HashSet<>();

    @NotNull
    @Positive
    private BigDecimal filamentGrams;

    private String imageUrl;

    private LocalDateTime createdDate;
}
