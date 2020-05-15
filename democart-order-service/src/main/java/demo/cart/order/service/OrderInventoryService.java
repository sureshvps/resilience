package demo.cart.order.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import demo.cart.order.model.InventoryResponse;
import lombok.extern.slf4j.Slf4j;


@Service
@RefreshScope
@Slf4j
public class OrderInventoryService {

	
	@Value("${inventory.service.reduce.url}")
	private String reduceInventoryURL;
	
	
	@Autowired
    RestTemplate restTemplate;
	
	
  @HystrixCommand(fallbackMethod = "reliable")
  public InventoryResponse reduceInventory(String skucode, int quantity) {
	  
	  String url = reduceInventoryURL+skucode+"/"+quantity;
	  log.info("The update inventory service URL : "+url);	 
	  return this.restTemplate.getForObject(url, InventoryResponse.class);
  }
  

  public InventoryResponse reliable() {
	  InventoryResponse invresponse = new InventoryResponse();
	  invresponse.setErrorCode("IN001");
	  invresponse.setStatus("failed");
	  invresponse.setErrorDesc("Inventory service unavailable");
	  return invresponse;
  }

}
