package demo.cart.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Document(collection = "books")
@Component
public class Products {
	
	@Id
    public String id;
	
	private String title;
	
	private String author;
	
	private String publisher;
	
	private String price;
	
	private String category;
	
	private String skucode;

}
