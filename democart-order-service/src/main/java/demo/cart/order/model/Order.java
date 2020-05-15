package demo.cart.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Document(collection = "order")
@Component
public class Order {
	
	@Id
    public String id;
	
	public String orderId;
	
	private String skucode;
	
	private int quantity;
	
	private String customerId;
	
	private String deleiveryAddressId;
	
	private String paymentDetailsId;
	
	private String orderstatus;

}
