package demo.cart.product.repository;

import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.cart.product.model.Products;


@Repository
@RefreshScope
public interface ProductRepository extends MongoRepository<Products, Long> {
	
	List<Products> findByAuthor(String author);
	
	List<Products> findByTitle(String title);

}
