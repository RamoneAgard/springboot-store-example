package com.lrtech.lrbackend.services;

import com.lrtech.lrbackend.models.ShippingDetail;
import com.lrtech.lrbackend.repositories.ShippingDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    private final ShippingDetailRepository shippingDetailRepository;

    @Override
    public ShippingDetail createNewShippingDetail(Map<String, String> shippingData) {
        ShippingDetail shipping = ShippingDetail.builder()
                .name(shippingData.get("name"))
                .country(shippingData.get("country"))
                .state(shippingData.get("state"))
                .city(shippingData.get("city"))
                .postalCode(shippingData.get("postalCode"))
                .line1(shippingData.get("line1"))
                .line2(shippingData.get("line2"))
                .build();
        return shippingDetailRepository.save(shipping);
    }
}
