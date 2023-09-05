package com.lrtech.lrbackend.services;

import com.lrtech.lrbackend.controllers.OrderRequestException;
import com.lrtech.lrbackend.dtoModels.OrderDetailDTO;
import com.lrtech.lrbackend.models.Order;
import com.lrtech.lrbackend.models.OrderDetail;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class StripeServiceImpl implements StripeService {


    @Value("${frontend.checkout.success}")
    private String successURL;

    @Value("${frontend.checkout.failure}")
    private String failureURL;

    @Value("${stripe.test.pk}")
    private String test_pk;

    @PostConstruct
    private void init(){
        Stripe.apiKey=test_pk;
    }

    @Override
    public SessionCreateParams createSessionParams(Order order) {
        SessionCreateParams.Builder builder = SessionCreateParams.builder();
        builder.setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successURL)
                .setCancelUrl(failureURL)
                .setShippingAddressCollection(SessionCreateParams.ShippingAddressCollection.builder()
                        .addAllowedCountry(SessionCreateParams.ShippingAddressCollection.AllowedCountry.US).build());
        for(OrderDetail od: order.getOrderDetails()) {
            builder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(od.getQuantity())
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd")
                                            .setUnitAmount(od.getUnitAmountInCent())
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName(od.getProduct().getProductName())
                                                            .setDescription("Print Plastic: " + od.getFilamentColor().getFilament().getType()
                                                                    + ", Color: " + od.getFilamentColor().getName())
                                                            .build()
                                            ).build()
                            ).build()
            );
        }
        Map<String, String> metaMap = new HashMap<>();
        metaMap.put("orderId", order.getId().toString());
        builder.putAllMetadata(metaMap);
        return builder.build();
    }

    @Override
    public String createCheckoutURL(Order order) {
        SessionCreateParams params = createSessionParams(order);
        String url;
        try {
            Session session = Session.create(params);
            url = session.getUrl();
        } catch (StripeException e) {
            log.info(e.getMessage());
            throw new OrderRequestException("Could not create stripe checkout session.");
        }
        return url;
    }

    @Override
    public SessionCreateParams createSessionParams(Set<OrderDetailDTO> orderDetails) {
        SessionCreateParams.Builder builder = SessionCreateParams.builder();
        builder.setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successURL)
                .setCancelUrl(failureURL)
                .setShippingAddressCollection(SessionCreateParams.ShippingAddressCollection.builder()
                        .addAllowedCountry(SessionCreateParams.ShippingAddressCollection.AllowedCountry.US).build());

        Map<String, String> metaMap = new HashMap<>();
        for(OrderDetailDTO od: orderDetails) {
            builder.addLineItem(
                    SessionCreateParams.LineItem.builder()
                            .setQuantity(od.getQuantity())
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("usd")
                                            .setUnitAmount(od.getUnitAmountInCent())
                                            .setProductData(
                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                            .setName(od.getProduct().getProductName())
                                                            .setDescription(od.getProduct().getDescription())
                                                            .build()
                                            ).build()
                            ).build()
            );
            metaMap.put(od.getProduct().getId().toString(), od.getQuantity().toString());
        }
        builder.putAllMetadata(metaMap);
        return builder.build();
    }

    @Override
    public Optional<Map<String, Map<String, String>>> getSessionData(Event event) {
        System.out.println("In stripe service getSessionData");
        if(event.getType().equals("checkout.session.completed")){
            System.out.println("session complete");
            Session sessionEvent = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
            if(sessionEvent == null){
                System.out.println("session null");
                return Optional.empty();

            }
            else if(sessionEvent.getPaymentStatus().equals("paid")){
                System.out.println("session paid");
                Map<String, Map<String, String>> sessionData = new HashMap<>();

                Map<String, String> statusInfo = new HashMap<>();
                statusInfo.put("message", "complete");
                sessionData.put("statusInfo", statusInfo);

                sessionData.put("orderInfo", sessionEvent.getMetadata());
                System.out.println(sessionData);

                Map<String, String> priceInfo = new HashMap<>();
                priceInfo.put("total", sessionEvent.getAmountSubtotal().toString());
                sessionData.put("priceInfo", priceInfo);

                Map<String, String> customerInfo = new HashMap<>();
                customerInfo.put("email", sessionEvent.getCustomerDetails().getEmail());
                customerInfo.put("name", sessionEvent.getCustomerDetails().getName());
                sessionData.put("customerInfo", customerInfo);

                Map<String,String> shippingInfo = new HashMap<>();
                shippingInfo.put("name", sessionEvent.getShippingDetails().getName());
                shippingInfo.put("country", sessionEvent.getShippingDetails().getAddress().getCountry());
                shippingInfo.put("state", sessionEvent.getShippingDetails().getAddress().getState());
                shippingInfo.put("city", sessionEvent.getShippingDetails().getAddress().getCity());
                shippingInfo.put("postalCode", sessionEvent.getShippingDetails().getAddress().getPostalCode());
                shippingInfo.put("line1", sessionEvent.getShippingDetails().getAddress().getLine1());
                shippingInfo.put("line2", sessionEvent.getShippingDetails().getAddress().getLine2());
                sessionData.put("shippingInfo", shippingInfo);

                return Optional.of(sessionData);
            }
        }
        else if(event.getType().equals("checkout.session.expired")){
            System.out.println("session expired");
            Session sessionEvent = (Session) event.getDataObjectDeserializer().getObject().orElse(null);
            if(sessionEvent == null){
                System.out.println("session null");
                return Optional.empty();
            }
            Map<String, Map<String, String>> sessionData = new HashMap<>();

            Map<String, String> statusInfo = new HashMap<>();
            statusInfo.put("message", "expired");
            sessionData.put("statusInfo", statusInfo);

            sessionData.put("orderInfo", sessionEvent.getMetadata());
            return Optional.of(sessionData);
        }
        else {
            Map<String, Map<String, String>> sessionData = new HashMap<>();

            Map<String, String> statusInfo = new HashMap<>();
            statusInfo.put("message", "other");
            sessionData.put("statusInfo", statusInfo);

            return Optional.of(sessionData);
        }
        return Optional.empty();
    }
}
