package com.poc.stripe_poc.service.impl;

import com.poc.stripe_poc.service.PaymentIntentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentIntentServiceImpl implements PaymentIntentService {

    @Override
    public PaymentIntent create() {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(1000L)
                .setCurrency("USD")
                .build();
        PaymentIntent intent = null;
        try {
            intent = PaymentIntent.create(params);
        System.out.println(intent.getId());
        System.out.println(intent.getStatus());
        } catch (StripeException e) {
            System.out.println(e);
        }
        return intent;
    }
    public void confirm(String id){
        PaymentIntentConfirmParams params =
                PaymentIntentConfirmParams.builder()
                        .setPaymentMethod("pm_card_visa")
                        .build();

        try {
            PaymentIntent intent = PaymentIntent.retrieve(id);

            PaymentIntent confirmedIntent =
                intent.confirm(params);
            System.out.println(confirmedIntent.getId());
            System.out.println(confirmedIntent.getStatus());

            } catch (StripeException e) {
            System.out.println(e);
            }

    }
}
