package demo.cart.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import demo.cart.inventory.model.Inventory;
import demo.cart.inventory.model.InventoryResponse;
import demo.cart.inventory.repository.InventoryRepository;

@RefreshScope
@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository repository;
	
	public InventoryResponse increaseInventory (String skucode, int quantity) {
		Inventory inventory = repository.findBySkucode(skucode);
		InventoryResponse invResponse = new InventoryResponse();
		if(inventory != null) {			
				inventory.setQuantity(inventory.getQuantity() + quantity);
				repository.save(inventory);
				invResponse.setInventory(inventory);
				invResponse.setStatus("success");	
			
		} else {
			invResponse.setStatus("failed");
			invResponse.setErrorCode("10002");
			invResponse.setErrorDesc("The product not found");
		}
		return invResponse;
	}
	
	public boolean reduceInventory (Inventory inventory, String skucode, int quantity) {

		if(inventory != null) {
			if(inventory.getQuantity() > 5) {
				inventory.setQuantity(inventory.getQuantity() - quantity);
				repository.save(inventory);			
				return true;
			}			
		} 
		return false;
			
	}
	
	public Inventory getInventory (String skucode) {
		Inventory inventory = repository.findBySkucode(skucode);
		return inventory;
	}

}
