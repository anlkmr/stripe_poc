package com.poc.stripe_poc;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.model.CustomerSearchResult;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerListParams;
import com.stripe.param.CustomerSearchParams;
import com.stripe.param.CustomerUpdateParams;

import com.poc.stripe_poc.service.PaymentIntentService;
import com.poc.stripe_poc.service.impl.PaymentIntentServiceImpl;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
//@EnableSwagger2
public class StripePocApplication {
	public static void main(String[] args) {
		SpringApplication.run(StripePocApplication.class, args);
		//PaymentIntentService paymentIntentService = new PaymentIntentServiceImpl();
		//PaymentIntent intent = paymentIntentService.create();
		//paymentIntentService.confirm("pi_3NcMdmSDgUA7ttRn1C9rltUN");

		//SpringApplication.run(StripePocApplication.class, args);
		//try {
			//createCustomer();
			//createCustomerWParams_PM_InvSettings();
			//getCustomers();
			//updateCustomer();
			//deleteCustomer();
			//searchCustomers();//temorarily not available in the region.
		//} catch(Exception e){
		//	System.out.println(e);
		//}
	}

	private static void createCustomerWParams_PM_InvSettings() {
		Stripe.apiKey = "sk_test_51NabVZSDgUA7ttRnPjZnj3MzZd4jgIgh8rElqXjNObzIKkqpav0xdafOXBbTNPqNyAne2atAbmmj8J3dhZPThdPf00xw6H79bK";

		CustomerCreateParams params = CustomerCreateParams.builder()
				.setPaymentMethod("pm_card_visa")
				.setInvoiceSettings(CustomerCreateParams.InvoiceSettings.builder()
						.setDefaultPaymentMethod("pm_card_visa")
						.build())
				.build();
		try {
			Customer customer = Customer.create(params);
		} catch (StripeException e) {
			System.out.println(e);
		}
	}

	private static void searchCustomers() {
		Stripe.apiKey = "sk_test_51NabVZSDgUA7ttRnPjZnj3MzZd4jgIgh8rElqXjNObzIKkqpav0xdafOXBbTNPqNyAne2atAbmmj8J3dhZPThdPf00xw6H79bK";

		CustomerSearchParams params =
				CustomerSearchParams
						.builder()
						.setQuery("name:'Test' AND metadata['order_id']:'67'")
						.build();

		try {
			CustomerSearchResult result = Customer.search(params);
		} catch (StripeException e) {
			System.out.println(e);
		}

	}

	private static void deleteCustomer() {
		Stripe.apiKey = "sk_test_51NabVZSDgUA7ttRnPjZnj3MzZd4jgIgh8rElqXjNObzIKkqpav0xdafOXBbTNPqNyAne2atAbmmj8J3dhZPThdPf00xw6H79bK";
		try{
			Customer customer =
					Customer.retrieve("cus_OORBMLWUzycS9X");

			Customer deletedCustomer =
					customer.delete();
			System.out.println(deletedCustomer);
		} catch(Exception e){
			System.out.println(e);
		}
	}

	private static void updateCustomer() {
		Stripe.apiKey = "sk_test_51NabVZSDgUA7ttRnPjZnj3MzZd4jgIgh8rElqXjNObzIKkqpav0xdafOXBbTNPqNyAne2atAbmmj8J3dhZPThdPf00xw6H79bK";
	try{
		Customer customer =
				Customer.retrieve("cus_OORto6umrCUoJM");

		Map<String, Object> metadata = new HashMap<>();
		/*metadata.put("order_id", "6735");
		Map<String, Object> params = new HashMap<>();
		params.put("metadata", metadata);*/
		CustomerUpdateParams params = new CustomerUpdateParams.Builder()
				.setName("TestCustomerNow")
				.setInvoiceSettings(
						CustomerUpdateParams
								.InvoiceSettings
								.builder()
								.addCustomField(
										CustomerUpdateParams
												.InvoiceSettings
												.CustomField
												.builder()
												.setName("VAT")
												.setValue("ABC123")
												.build()
								)
								.addCustomField(
										CustomerUpdateParams
												.InvoiceSettings
												.CustomField
												.builder()
												.setName("VAT2")
												.setValue("XYZ987")
												.build()
								)
						//.setDefaultPaymentMethod("pm_card_visa")
						.build())
				.build();
		Customer updatedCustomer =
				customer.update(params);
	} catch(Exception e){
		System.out.println(e);
	}
	}

	private static void getCustomers() {

			Stripe.apiKey = "sk_test_51NabVZSDgUA7ttRnPjZnj3MzZd4jgIgh8rElqXjNObzIKkqpav0xdafOXBbTNPqNyAne2atAbmmj8J3dhZPThdPf00xw6H79bK";
			//CustomerListParams params = CustomerListParams.builder().build();
		CustomerCollection customers = null;
		//Map<String, Object> params = new HashMap<>();
		CustomerListParams params = CustomerListParams.builder()
				//.setEmail("use the stored email")
				//.setLimit(3L)
				.build();
		//params.put("limit", 3);
		try {
			customers = Customer.list(params);
		} catch (StripeException e) {
			System.out.println(e);
		}
		System.out.println(customers);

	}

	private static void createCustomer() {
		Stripe.apiKey = "sk_test_51NabVZSDgUA7ttRnPjZnj3MzZd4jgIgh8rElqXjNObzIKkqpav0xdafOXBbTNPqNyAne2atAbmmj8J3dhZPThdPf00xw6H79bK";

		Map<String, Object> params = new HashMap<>();
		params.put(
				"description",
				"My First Test Customer (created for API docs at https://www.stripe.com/docs/api)"
		);

		try {
			Customer customer = Customer.create(params);
		} catch (StripeException e) {
			System.out.println(e);
		}

	}

}
