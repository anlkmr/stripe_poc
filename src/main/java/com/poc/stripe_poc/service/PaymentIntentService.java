package com.poc.stripe_poc.service;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;


public interface PaymentIntentService {
    PaymentIntent create();
    void confirm(String id);
}
