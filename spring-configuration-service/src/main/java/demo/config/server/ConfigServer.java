package demo.config.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer {
	
	
	public static void main(String[] arguments) {
        SpringApplication.run(ConfigServer.class, arguments);
    }
	
	 

}
