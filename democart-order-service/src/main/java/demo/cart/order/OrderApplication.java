package demo.cart.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import demo.cart.order.bus.Producer;
import demo.cart.order.model.InventoryResponse;
import demo.cart.order.model.OrderRequest;
import demo.cart.order.model.OrderResponse;
import demo.cart.order.service.OrderInventoryService;
import demo.cart.order.service.PaymentClientService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;


@RestController
@SpringBootApplication
@EnableFeignClients
@RequestMapping("orderprocess")
@ComponentScan("demo.cart.order.*")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@RefreshScope
@Slf4j
public class OrderApplication {
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private OrderInventoryService orderService;
	
	@Autowired
	private PaymentClientService paymentService;


	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
	
  
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = {"application/json", "application/xml"}
    ,  consumes = {"application/json", "application/xml"})
	public OrderResponse createOrder(@RequestBody OrderRequest request) {
		Map<String, Integer> itemMap = request.getItemMap();
		for(Map.Entry<String,Integer> entry :itemMap.entrySet()) {
			if (entry.getValue() > 0 && entry.getKey() != null) {
		          
	        	System.out.println("The ineventory service ");
	        	InventoryResponse response = orderService.reduceInventory(entry.getKey(), entry.getValue());
	        	log.info("The inventory service response "+ response);
	        	OrderResponse orderResponse = new OrderResponse();
	        	orderResponse.setStatus("failed");
	        	if(response.getStatus().equals("success")) {
	        		try {
	        			ResponseEntity<String> paymentResponse = paymentService.paymentService();
	        			if(HttpStatus.OK.equals((paymentResponse.getStatusCode()))) {	        			
		    	        	orderResponse.setStatus(response.getStatus());
		        		}else {
		        			orderResponse.setErrorCode("12345");
			        		orderResponse.setErrorDesc(paymentResponse.getBody());
			        		producer.sendMessage("hellow message");
		        		}
	        		} catch(HttpClientErrorException e) {
	        			orderResponse.setErrorCode("12345");
		        		orderResponse.setErrorDesc(e.getResponseBodyAsString());
		        		producer.sendMessage(entry.getKey()+"-"+entry.getValue());
	        		}
	        		
	        		 
	        	} else {
	        		if("failed".equals(response.getStatus()) ) {
		        		
		        		orderResponse.setErrorCode(response.getErrorCode());
		        		orderResponse.setErrorDesc(response.getErrorDesc());
		        		
		        	} 
	        	}
	        		        	
	        	System.out.println("success: "+ orderResponse);	        	
	        	return orderResponse;
	            
	        }
		}
        
       return null;
    }
  
}
