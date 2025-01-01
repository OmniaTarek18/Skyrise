package com.example.backend.Services;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Profile("!test")
public class StripeService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public String createPaymentIntent(int amount, String currency) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        // Create PaymentIntent parameters
        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount); // Amount in smallest currency unit (e.g., cents for USD)
        params.put("currency", currency);

        // Create the PaymentIntent
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Return the client secret
        return paymentIntent.getClientSecret();
    }
}
