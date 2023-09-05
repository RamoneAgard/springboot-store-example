package com.lrtech.lrbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

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
    private OrderStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    @Valid
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @ManyToOne
    @Valid
    private Customer customer;

    @Valid
    @OneToOne
    private ShippingDetail shippingDetail;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @Version
    private Integer version;

    @JsonGetter("totalPrice")
    @Transient
    private BigDecimal getTotalPrice(){
        BigDecimal sum = BigDecimal.ZERO;
        for(OrderDetail od: orderDetails){
            sum = sum.add(od.getProduct().getPrice());
        }
        return sum;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
        customer.getCustomerOrders().add(this);
    }

    public void setShippingDetail(ShippingDetail shippingDetail){
        this.shippingDetail = shippingDetail;
        shippingDetail.setOrder(this);
    }
}
