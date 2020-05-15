package demo.cart.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@RefreshScope
@EnableMongoRepositories({"demo.cart.order.repository"})
public class SimpleMongoConfig  {
	
	@Value("${spring.data.mongodb.database}") String dbName;
	
	@Value("${spring.data.mongodb.host}") String host;
  
	@Bean
    public MongoClient mongo() {
        return new MongoClient(host);
    }
 
    @Bean()
    @Primary
    @RefreshScope
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), dbName);
    }
}
