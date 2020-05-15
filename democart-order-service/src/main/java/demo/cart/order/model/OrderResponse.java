package demo.cart.order.model;


import lombok.Data;

@Data
public class OrderResponse {
	
	private Order order;
	
	private String status;
	
	private String errorCode;
	
	private String errorDesc;

}
