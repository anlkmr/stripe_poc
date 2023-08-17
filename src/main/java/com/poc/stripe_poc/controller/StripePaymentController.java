package com.poc.stripe_poc.controller;

import com.poc.stripe_poc.model.CustomerData;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerListParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin()
@Controller
public class StripePaymentController {

    @Value("${stripe.apikey}")
    String stripeKey;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/customer")
    public String customer(Model model) throws StripeException {
        Stripe.apiKey = stripeKey;
        //CustomerListParams params = CustomerListParams.builder().build();
        //Map<String, Object> params = new HashMap<>(); added for test
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
        model.addAttribute("customers", allCustomers);
        return "customer";
    }
    @RequestMapping("/index")
    public String index(Model model) throws StripeException {
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
        model.addAttribute("customers", allCustomers);

        return "index";
    }

    @RequestMapping("/createCustomer")
    public String createCustomer(CustomerData customerData){
        return "create-customer";
    }

    @RequestMapping("/addCustomer")
    public String addCustomer(CustomerData customerData) throws StripeException {

            Stripe.apiKey=stripeKey;
            CustomerCreateParams params = CustomerCreateParams.builder()
                    .setName(customerData.getName())
                    .setDescription(customerData.getDescription())
                    .setEmail(customerData.getEmail())
                    .build();

                Customer customerCreated = Customer.create(params);

        return "success";
    }



}
