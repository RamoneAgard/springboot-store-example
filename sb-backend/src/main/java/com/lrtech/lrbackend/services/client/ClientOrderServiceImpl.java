package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.OrderDTO;
import com.lrtech.lrbackend.mappers.OrderMapper;
import com.lrtech.lrbackend.models.Order;
import com.lrtech.lrbackend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<OrderDTO> getOrderById(UUID id) {
        return Optional.ofNullable(
                orderMapper.orderToOrderDto(
                        orderRepository.findById(id)
                                .orElse(null)));
    }

    @Override
    public Optional<Order> retrieveOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    @Override
    public boolean expireOrderById(UUID id) {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

}
