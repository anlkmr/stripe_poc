package com.poc.stripe_poc.controller;

import com.poc.stripe_poc.model.CustomerData;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin() // open for all ports
@RestController
@RequestMapping("/api")
public class StripePaymentControllerAPI {

    @Value("${stripe.apikey}")
    String stripeKey;
    @PostMapping(path = "/createcustomer")
    public CustomerData createCustomer(@RequestBody CustomerData data){
        Stripe.apiKey=stripeKey;
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName("Demo1")
                .setDescription("testing now")
                .setEmail("a@c.com")
                .build();
        try {
            Customer customerCreated = Customer.create(params);
            data.setCustomerId(customerCreated.getId());
            data.setEmail(customerCreated.getEmail());
            data.setName(customerCreated.getName());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
