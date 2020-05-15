package demo.cart.payment.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.cloud.context.config.annotation.RefreshScope;


@RestController
@SpringBootApplication
@RequestMapping("paymentgateway")
@ComponentScan("demo.cart.*")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@RefreshScope
public class PaymentApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
	
  

	@RequestMapping(value = "/payment")
	public ResponseEntity<String> payment() {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Payment authorization failed");
	}
	
  
}
