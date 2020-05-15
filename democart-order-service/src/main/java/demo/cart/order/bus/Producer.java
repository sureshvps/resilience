package demo.cart.order.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Producer {
	
	private static final String TOPIC = "greetings";
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	public void sendMessage(String message){
	log.info(String.format("$$ -> Producing message --> %s",message));
	this.kafkaTemplate.send(TOPIC,message);
	}

}
