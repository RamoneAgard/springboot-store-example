package com.lrtech.lrbackend.dtoModels;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;


@Data
public class OrderForm {

    @Valid
    @NotNull
    private Set<ClientOrderDetailDTO> orderDetails;

    @Valid
    private CustomerDTO customer;
}
