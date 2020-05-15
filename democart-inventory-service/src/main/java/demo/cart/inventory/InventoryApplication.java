package demo.cart.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import demo.cart.inventory.model.Inventory;
import demo.cart.inventory.model.InventoryResponse;
import demo.cart.inventory.service.InventoryService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@SpringBootApplication
@RequestMapping("inventorystore")
@ComponentScan("demo.cart.inventory.*")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class InventoryApplication {
	
	@Autowired
	private InventoryService inventoryService;


	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
	
	
	/*
	 * Method call with HTTP code response
	@RequestMapping(value = "/updateInventory/{skucode}/{quantity}")
	public ResponseEntity<Inventory> listAvailableBooks(@PathVariable String skucode, @PathVariable int quantity) {
		Inventory inventory = repository.findBySkucode(skucode);
		if(inventory != null) {
			if(inventory.getQuantity() > 8) {
				inventory.setQuantity(inventory.getQuantity() - quantity);
				repository.save(inventory);
				return ResponseEntity.ok(inventory);
			}
			else {
				return ResponseEntity.status(HttpStatus.GONE).body(null);
			}
			
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	*/
	
	@RequestMapping(value = "/updateInventory/{skucode}/{quantity}")
	public ResponseEntity<InventoryResponse> updateInventory(@PathVariable String skucode, @PathVariable int quantity) {
		Inventory inventory = inventoryService.getInventory(skucode);
		InventoryResponse invResponse = new InventoryResponse();
		if(inventory != null) {
			boolean updatestatus = inventoryService.reduceInventory(inventory, skucode, quantity);
			if(updatestatus) {
				invResponse.setInventory(inventory);
				invResponse.setStatus("success");
			}
			else {
				invResponse.setStatus("failed");
				invResponse.setErrorCode("10001");
				invResponse.setErrorDesc("The inventory not available");
			}
			
		} else {
			invResponse.setStatus("failed");
			invResponse.setErrorCode("10002");
			invResponse.setErrorDesc("The product not found");
		}
		return ResponseEntity.ok(invResponse);
	}
	
  
  
}
