package com.poc.stripe_poc.controller;

import com.poc.stripe_poc.model.CustomerData;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerListParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
    @GetMapping("/getAllCustomers")
    public List<CustomerData> getAllCustomers() throws StripeException {
        Stripe.apiKey = stripeKey;
        //CustomerListParams params = CustomerListParams.builder().build();
        //Map<String, Object> params = new HashMap<>();
        CustomerListParams params = CustomerListParams.builder()
                //.setEmail("use the stored email")
                .setLimit(3L)
                .build();
        //params.put("limit", 3);
        CustomerCollection customers = Customer.list(params);
        List<CustomerData> allCustomers = new ArrayList<CustomerData>();
        for(Customer customerResult:customers.getData()){
            CustomerData customerData = new CustomerData();
            customerData.setCustomerId(customerResult.getId());
            customerData.setName(customerResult.getName());
            customerData.setDescription(customerResult.getDescription());
            customerData.setEmail(customerResult.getEmail());
            allCustomers.add(customerData);
        }
        return allCustomers;
    }

}
