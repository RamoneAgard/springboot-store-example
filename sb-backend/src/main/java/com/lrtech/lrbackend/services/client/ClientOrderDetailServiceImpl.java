package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.OrderDetailDTO;
import com.lrtech.lrbackend.mappers.OrderDetailMapper;
import com.lrtech.lrbackend.models.OrderDetail;
import com.lrtech.lrbackend.repositories.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
@Service
public class ClientOrderDetailServiceImpl implements ClientOrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    private final OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDetail saveOrderDetail(@Validated OrderDetailDTO orderDetailDTO) {
        return orderDetailRepository.save(
                orderDetailMapper.orderDetailDtoToOrderDetail(
                        orderDetailDTO));
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
