package demo.cart.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@RefreshScope
@Slf4j
public class PaymentClientService {
	

	@Value("${payment.service.url}")
	private String paymentServiceURL;
	
	@Autowired
    RestTemplate restTemplate;
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> paymentService() {
	  
	  String url = paymentServiceURL;
	  log.info("The update inventory service URL : "+url);	 
	  return this.restTemplate.getForObject(url, ResponseEntity.class);
	}


}
