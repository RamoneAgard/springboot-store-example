package com.lrtech.lrbackend.controllers;

import com.lrtech.lrbackend.services.client.ClientRestService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@PropertySource("classpath:application.properties")
public class WebhookController {

    private final ClientRestService clientRestService;

    @Value("${stripe.test.webhook.secret}")
    private String webhookSec;

    public static final String WEBHOOK_PATH = "/api/lrwebhook";

    @PostMapping(WEBHOOK_PATH)
    public ResponseEntity checkoutCompleteWebhook(@RequestHeader("Stripe-Signature") String strSig, @RequestBody String info){
        System.out.println("In controller for hook");
        Event event = null;
        try {
            event = Webhook.constructEvent(info, strSig, webhookSec);
        } catch (SignatureVerificationException e) {
            System.out.println("signatureVerificationException");
            throw new OrderRequestException("Could not verify request source", e);
        }
        return clientRestService.handleSessionEvent(event) ?
                new ResponseEntity(HttpStatus.OK):
                new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
