package demo.cart.bus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GreetingsService {

	 @Autowired
	 private OrderService greetingsStreams;
	  
	    public void sendGreeting(final String greetings) {
	        log.info("Sending greetings {}", greetings);
	        try {
	        	MessageChannel messageChannel = greetingsStreams.outboundGreetings();
		        messageChannel.send(MessageBuilder
		                .withPayload(greetings)
		                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN)
		                .build());
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	    }
}
