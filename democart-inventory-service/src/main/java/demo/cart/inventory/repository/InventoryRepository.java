package demo.cart.inventory.repository;


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import demo.cart.inventory.model.Inventory;


@Repository
@RefreshScope
public interface InventoryRepository extends MongoRepository<Inventory, Long> {
	
	Inventory findBySkucode(String skucode);

}
