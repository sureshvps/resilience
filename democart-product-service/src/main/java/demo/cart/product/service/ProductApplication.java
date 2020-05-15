package demo.cart.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;



import demo.cart.product.model.Products;


@RestController
@SpringBootApplication
@RequestMapping("productstore")
@ComponentScan("demo.cart.product.*")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@RefreshScope
public class ProductApplication {
	
	@Autowired	
	private MongoTemplate mongoTemplate;


	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@RequestMapping(value = "/listOfBooks")
	
	  public List<Products> listAvailableBooks(){
		return mongoTemplate.findAll(Products.class);
	    //return mongoTemplate.findAll(Products.class).stream().map(Products::getTitle).collect(Collectors.toList());
	  }
  
  
}
