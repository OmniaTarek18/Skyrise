package com.example.backend.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Services.StripeService;
import com.stripe.exception.StripeException;







@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> paymentData) {
        int amount = (int) paymentData.get("amount");
        String currency = (String) paymentData.get("currency");

        try {
            String clientSecret = stripeService.createPaymentIntent(amount, currency);
            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", clientSecret);
            return response;
        } catch (StripeException e) {
            throw new RuntimeException("Error creating PaymentIntent", e);
        }
    }
}
