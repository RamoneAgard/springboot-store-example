package com.lrtech.lrbackend.services;

import com.lrtech.lrbackend.models.ShippingDetail;

import java.util.Map;

public interface ShippingService {

    ShippingDetail createNewShippingDetail(Map<String, String> shippingData);
}
