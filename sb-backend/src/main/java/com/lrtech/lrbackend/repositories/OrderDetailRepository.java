package com.lrtech.lrbackend.repositories;


import com.lrtech.lrbackend.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {

}
