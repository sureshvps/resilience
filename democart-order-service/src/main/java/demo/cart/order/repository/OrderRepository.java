package demo.cart.order.repository;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.cart.order.model.Order;


@Repository
@RefreshScope
public interface OrderRepository extends MongoRepository<Order, Long> {
	
	Order findBySkucode(String skucode);

}
