package com.poc.stripe_poc;

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


@SpringBootApplication
//@EnableSwagger2
public class StripePocApplication {


	public static void main(String[] args) {

		SpringApplication.run(StripePocApplication.class, args);
		//PaymentIntentService paymentIntentService = new PaymentIntentServiceImpl();
		//PaymentIntent intent = paymentIntentService.create();
		//paymentIntentService.confirm("pi_3NcMdmSDgUA7ttRn1C9rltUN");
	}

}
