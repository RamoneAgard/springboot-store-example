package com.lrtech.lrbackend.controllers;

import com.lrtech.lrbackend.dtoModels.OrderDTO;
import com.lrtech.lrbackend.dtoModels.OrderForm;
import com.lrtech.lrbackend.models.Order;
import com.lrtech.lrbackend.services.client.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
//@RequestMapping("/api/order")
public class ClientOrderController {

    private final ClientRestService clientRestService;

    private final ClientOrderService clientOrderService;

    private final ClientOrderDetailService clientOrderDetailService;

    private final ClientProductService clientProductService;

    private final ClientCustomerService clientCustomerService;

    public static final String CLIENT_ORDER_PATH = "/api/order";

    public static final String ORDER_ID_PARAM = "orderId";

    public static final String CLIENT_ORDER_ID_PATH = CLIENT_ORDER_PATH + "/{" + ORDER_ID_PARAM + "}";

    @PostMapping(CLIENT_ORDER_PATH)
    @Transactional
    public ResponseEntity<OrderDTO> createOrder(@Validated @RequestBody OrderForm orderForm){
        Order newOrder = clientRestService.createAndSaveOrder(orderForm);
        String checkoutURL = clientRestService.requestPayment(newOrder);

        Map<String, String> response = new HashMap<>();
        response.put("redirect_url", checkoutURL);

        return new ResponseEntity(response, HttpStatus.CREATED);

    }

    @GetMapping(CLIENT_ORDER_ID_PATH)
    public OrderDTO getOrderById(@PathVariable(ORDER_ID_PARAM) UUID orderId){
        return clientRestService.getOrderById(orderId);
    }

//    @PostMapping(CLIENT_ORDER_PATH)
//    public ResponseEntity goToPayment(@Validated @RequestBody OrderForm orderForm){
////        String checkoutURL = clientRestService.requestPayment(orderForm);
//        Map<String, String> response = new HashMap<>();
//        response.put("redirect_url", "");
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
}
