package com.lrtech.lrbackend.dtoModels;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CustomerDTO {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    private LocalDateTime createdDate;
}
