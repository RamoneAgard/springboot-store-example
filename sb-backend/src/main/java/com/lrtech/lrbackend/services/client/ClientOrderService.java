package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.OrderDTO;
import com.lrtech.lrbackend.models.Order;

import java.util.Optional;
import java.util.UUID;

public interface ClientOrderService {

    Order saveOrder(Order order);

    Optional<OrderDTO> getOrderById(UUID id);

    Optional<Order> retrieveOrderById(UUID id);

    boolean expireOrderById(UUID id);

}
