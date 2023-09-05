package com.lrtech.lrbackend.repositories;

import com.lrtech.lrbackend.models.ShippingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingDetailRepository extends JpaRepository<ShippingDetail, UUID> {
}
