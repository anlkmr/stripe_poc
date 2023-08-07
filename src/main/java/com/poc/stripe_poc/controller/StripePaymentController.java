package com.poc.stripe_poc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin()
@RestController
public class StripePaymentController {

    @Value("${stripe.apikey}")
    String stripeKey;

    @RequestMapping("/")
    public String index(){
        return "hello "+stripeKey;
    }
}
