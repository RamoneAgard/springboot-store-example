package com.lrtech.lrbackend.services;

import com.lrtech.lrbackend.dtoModels.OrderDetailDTO;
import com.lrtech.lrbackend.models.Order;
import com.stripe.model.Event;
import com.stripe.param.checkout.SessionCreateParams;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface StripeService {

    SessionCreateParams createSessionParams(Order order);

    String createCheckoutURL(Order order);

    SessionCreateParams createSessionParams(Set<OrderDetailDTO> orderDetails);

    Optional<Map<String, Map<String, String>>> getSessionData(Event event);

}
