package demo.cart.bus.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderService {
	
	String INPUT = "greetings-in";
    String OUTPUT = "greetings-out";
    @Input(INPUT)
    SubscribableChannel inboundGreetings();
    @Output(OUTPUT)
    MessageChannel outboundGreetings();

}
