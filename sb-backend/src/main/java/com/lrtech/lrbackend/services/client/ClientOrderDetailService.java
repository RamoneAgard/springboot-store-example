package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.OrderDetailDTO;
import com.lrtech.lrbackend.models.OrderDetail;

public interface ClientOrderDetailService {
    OrderDetail saveOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetail saveOrderDetail(OrderDetail orderDetail);

}
