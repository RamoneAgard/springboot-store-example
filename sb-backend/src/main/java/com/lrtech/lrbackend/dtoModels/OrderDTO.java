package com.lrtech.lrbackend.dtoModels;

import com.lrtech.lrbackend.models.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class OrderDTO {

    private UUID id;

    @NotNull
    private OrderStatus status;

    @Builder.Default
    @Valid
    private Set<OrderDetailDTO> orderDetails = new HashSet<>();

    @Valid
    private CustomerDTO customer;

    @NotNull
    private BigDecimal totalPrice;

    @Valid
    @NotNull
    private ShippingDetailDTO shippingDetail;

    private LocalDateTime createdDate;
}
