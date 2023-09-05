package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.controllers.NotFoundRequestException;
import com.lrtech.lrbackend.controllers.OrderRequestException;
import com.lrtech.lrbackend.dtoModels.ClientOrderDetailDTO;
import com.lrtech.lrbackend.dtoModels.OrderDTO;
import com.lrtech.lrbackend.dtoModels.OrderForm;
import com.lrtech.lrbackend.models.*;
import com.lrtech.lrbackend.services.FilamentColorService;
import com.lrtech.lrbackend.services.ShippingService;
import com.lrtech.lrbackend.services.StripeService;
import com.stripe.model.Event;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClientRestServiceImpl implements ClientRestService {

    private final ClientCustomerService clientCustomerService;

    private final ClientProductService clientProductService;

    private final ClientOrderService clientOrderService;

    private final ClientOrderDetailService clientOrderDetailService;

    private final ShippingService shippingService;

    private final StripeService stripeService;

    private final FilamentColorService filamentColorService;

    @Override
    @Transactional
    public Order createAndSaveOrder(@Validated OrderForm orderForm) {
        System.out.println("orderForm:" + orderForm.toString());
        //Customer newCustomer = clientCustomerService.saveCustomer(orderForm.getCustomer());

        // Create new Orders
        Order newOrder = new Order();
        newOrder.setStatus(OrderStatus.CREATED);
        newOrder = clientOrderService.saveOrder(newOrder);

        //validate products for each orderDetail and add to new order
        Set<OrderDetail> orderDetailSet = new HashSet<>();
        for(ClientOrderDetailDTO detail: orderForm.getOrderDetails()){
            Optional<Product> product = clientProductService.retrieveProduct(detail.getProduct().getId());
            if(clientProductService.validateDto(detail.getProduct())){
                OrderDetail newDetail =  clientOrderDetailService.saveOrderDetail(
                        OrderDetail.builder()
                                .order(newOrder)
                                .product(product.orElseThrow(NotFoundRequestException::new))
                                .quantity(detail.getQuantity())
                                .filamentColor(filamentColorService.getById(
                                        detail.getColor().getId())
                                        .orElseThrow(NotFoundRequestException::new))
                                .build()
                );
                orderDetailSet.add(newDetail);
            }
            else {
                throw new OrderRequestException("Could not validate ordered products.");
            }
        }
        //save new order and return to controller
        newOrder.setOrderDetails(orderDetailSet);

        return clientOrderService.saveOrder(newOrder);
    }

    @Override
    public OrderDTO getOrderById(UUID id) {
        return clientOrderService.getOrderById(id)
                .orElseThrow(NotFoundRequestException::new);
    }

    @Override
    public String requestPayment(@Validated Order order) {
        return stripeService.createCheckoutURL(order);
    }

    @Override
    @Transactional
    public boolean updateCompleteSession(Map<String, Map<String, String>> sessionData) {
        System.out.println("in rest service updateCompleteSession");

        Order sessionOrder = clientOrderService.retrieveOrderById(UUID.fromString(
                sessionData.get("orderInfo").get("orderId")))
                .orElseThrow(NotFoundRequestException::new);

        Customer newCustomer = clientCustomerService.createNewCustomer(sessionData.get("customerInfo"));
        ShippingDetail newShippingDetail = shippingService.createNewShippingDetail(sessionData.get("shippingInfo"));

        sessionOrder.setCustomer(newCustomer);
        sessionOrder.setShippingDetail(newShippingDetail);

        Set<OrderDetail> orderDetailSet = new HashSet<>();
        Long orderTotal = 0L;

        for(OrderDetail od: sessionOrder.getOrderDetails()){
            orderTotal += (od.getUnitAmountInCent() * od.getQuantity());
        }

        if(!orderTotal.toString().equals(sessionData.get("priceInfo").get("total"))){
            System.out.println("order total: " + orderTotal);
            System.out.println("session total: " + sessionData.get("priceInfo").get("total"));
            System.out.println("price info wrong");
            throw new OrderRequestException("Could not verify session charge amount.");
        };
        sessionOrder.setStatus(OrderStatus.PAID);
        clientOrderService.saveOrder(sessionOrder);
//        System.out.println(clientOrderService.saveOrder(newOrder).getStatus());
        return true;
    }

    @Override
    public boolean updateExpiredSession(Map<String, Map<String, String>> sessionData) {
        Order sessionOrder = clientOrderService.retrieveOrderById(UUID.fromString(
                sessionData.get("orderInfo").get("orderId")))
                .orElseThrow(NotFoundRequestException::new);
        return clientOrderService.expireOrderById(sessionOrder.getId());
    }

    @Override
    public boolean handleSessionEvent(Event event) {
        Map<String, Map<String, String>> sessionData = stripeService.getSessionData(event).orElse(null);
        if(sessionData == null){
            System.out.println("sessionData null");
            throw new OrderRequestException("Could not retrieve completed checkout session data.");
        }
        String sessionStatus = sessionData.get("statusInfo").get("message");
        if(sessionStatus.equals("complete")){
            return updateCompleteSession(sessionData);
        }
        else if (sessionStatus.equals("expired")) {
            return updateExpiredSession(sessionData);
        }
        else if (sessionStatus.equals("other")) {
            return true;
        }
        return false;
    }


}
