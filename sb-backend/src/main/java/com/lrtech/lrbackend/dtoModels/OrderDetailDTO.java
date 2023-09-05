package com.lrtech.lrbackend.dtoModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class OrderDetailDTO {

    private UUID id;

    @NotNull
    @Valid
    private ProductDTO product;

    @NotNull
    @Positive
    private Long quantity;

    @Valid
    @NotNull
    private OrderFilamentDTO orderFilament;

    @JsonIgnore
    private OrderDTO order;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public Long getUnitAmountInCent(){
        return product.getPrice().movePointRight(2).longValue();
    }
}
