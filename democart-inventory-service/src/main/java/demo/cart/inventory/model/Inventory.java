package demo.cart.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Document(collection = "inventories")
@Component
public class Inventory {
	
	@Id
    public String id;
	
	private String skucode;
	
	private int quantity;

}
