package com.lrtech.lrbackend.dtoModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShippingDetailDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Builder.Default
    private String country = "US";

    @NotNull
    @NotBlank
    private String state;

    @NotNull
    @NotBlank
    private String city;

    @NotNull
    @NotBlank
    private String postalCode;

    @NotNull
    @NotBlank
    private String line1;

    private String line2;

    private LocalDateTime createdDate;
}
