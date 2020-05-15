package demo.cart.inventory.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import demo.cart.inventory.model.InventoryResponse;
import demo.cart.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {
	
	@Autowired
	InventoryService inventoryService;
	
	@KafkaListener(topics = "greetings", groupId = "group_id")
	public void consume(String message){
	log.info(String.format("$$ -> Consumed Message -> %s",message));
	String str[] = message.split("-");
	try {
		Thread.sleep(30000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	InventoryResponse response = inventoryService.increaseInventory(str[0], Integer.parseInt(str[1]));
	log.info(" Inventory response "+ response);
	
	}

}
