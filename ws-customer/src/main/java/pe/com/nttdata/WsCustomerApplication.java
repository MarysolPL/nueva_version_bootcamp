package pe.com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WsCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsCustomerApplication.class, args);
	}

}
