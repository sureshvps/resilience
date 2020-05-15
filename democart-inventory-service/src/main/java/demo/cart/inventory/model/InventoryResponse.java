package demo.cart.inventory.model;


import lombok.Data;

@Data
public class InventoryResponse {
	
	private Inventory inventory;
	
	private String status;
	
	private String errorCode;
	
	private String errorDesc;

}
