package com.lrtech.lrbackend.mappers;

import com.lrtech.lrbackend.dtoModels.OrderDTO;
import com.lrtech.lrbackend.models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProductMapper.class, OrderDetailMapper.class, CustomerMapper.class})
public interface OrderMapper {

    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    Order orderDtoToOrder(OrderDTO dto);

    OrderDTO orderToOrderDto(Order order);


}
