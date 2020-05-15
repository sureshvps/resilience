package demo.cart.bus.service;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(OrderService.class)
public class StreamsConfig {

}
