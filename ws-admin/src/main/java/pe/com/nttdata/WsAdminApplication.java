package pe.com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class WsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsAdminApplication.class, args);
	}

}
