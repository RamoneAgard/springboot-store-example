package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.OrderDTO;
import com.lrtech.lrbackend.dtoModels.OrderForm;
import com.lrtech.lrbackend.models.Order;
import com.stripe.model.Event;

import java.util.Map;
import java.util.UUID;

public interface ClientRestService {
    Order createAndSaveOrder(OrderForm orderForm);

    OrderDTO getOrderById(UUID id);

    String requestPayment(Order order);

    boolean updateCompleteSession(Map<String, Map<String, String>> sessionData);

    boolean updateExpiredSession(Map<String, Map<String, String>> sessionData);

    boolean handleSessionEvent(Event event);

}
