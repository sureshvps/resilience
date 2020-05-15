package demo.cart.order.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class OrderRequest {
	
	private Map<String, Integer> itemMap = new HashMap<String, Integer>();
	
	private String customerId;
	
	private String deleiveryAddressId;
	
	private String paymentDetailsId;

}
