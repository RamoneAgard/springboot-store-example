package com.lrtech.lrbackend.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotNull
    @Valid
    @ManyToOne
    private Product product;

    @NotNull
    @Positive
    @Max(15)
    private Long quantity;

    @NotNull
    @Valid
    @ManyToOne
    private FilamentColor filamentColor;

    @NotNull
    @Valid
    @ManyToOne
    private Order order;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @Version
    private Integer version;

    @Transient
    public Long getUnitAmountInCent(){
        return product.getPrice().movePointRight(2).longValue();
    }


}
