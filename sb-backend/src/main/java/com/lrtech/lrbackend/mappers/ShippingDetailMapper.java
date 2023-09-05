package com.lrtech.lrbackend.mappers;

import com.lrtech.lrbackend.dtoModels.ShippingDetailDTO;
import com.lrtech.lrbackend.models.ShippingDetail;
import org.mapstruct.Mapper;

@Mapper
public interface ShippingDetailMapper {

    ShippingDetailDTO shippingDetailToShippingDetailDTO(ShippingDetail shippingDetail);
}
