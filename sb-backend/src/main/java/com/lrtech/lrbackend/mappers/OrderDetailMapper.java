package com.lrtech.lrbackend.mappers;

import com.lrtech.lrbackend.dtoModels.OrderDetailDTO;
import com.lrtech.lrbackend.models.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProductMapper.class})
public interface OrderDetailMapper {

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    OrderDetail orderDetailDtoToOrderDetail(OrderDetailDTO orderDetailDTO);

    @Mapping(target = "order", ignore = true)
    OrderDetailDTO orderDetailToOrderDetailDto(OrderDetail orderDetail);

}
