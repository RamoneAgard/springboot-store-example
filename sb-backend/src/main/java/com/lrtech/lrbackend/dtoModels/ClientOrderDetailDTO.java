package com.lrtech.lrbackend.dtoModels;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientOrderDetailDTO {

    @NotNull
    @Valid
    private ProductDTO product;

    @NotNull
    @Positive
    private Long quantity;

    @NotNull
    @Valid
    private FilamentColorDTO color;
}
