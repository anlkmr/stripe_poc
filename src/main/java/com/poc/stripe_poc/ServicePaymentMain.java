package com.poc.stripe_poc;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.HashMap;
import java.util.Map;

public class ServicePaymentMain {

    public static void main(String[] args) {
        Stripe.apiKey = "sk_test_51NabVZSDgUA7ttRnPjZnj3MzZd4jgIgh8rElqXjNObzIKkqpav0xdafOXBbTNPqNyAne2atAbmmj8J3dhZPThdPf00xw6H79bK";

        Map<String, Object> automaticPaymentMethods =
                new HashMap<>();
        automaticPaymentMethods.put("enabled", true);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 2000);
        params.put("currency", "usd");
        params.put(
                "automatic_payment_methods",
                automaticPaymentMethods
        );

        try {
            PaymentIntent paymentIntent =
                    PaymentIntent.create(params);
        } catch (StripeException e) {
            System.out.println(e);
        }
    }
}
