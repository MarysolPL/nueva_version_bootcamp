package pe.com.nttdata.router;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.nttdata.handler.TypeCustomerHandler;

@Configuration
public class TypeCustomerRouter {
	@Bean
	public RouterFunction<ServerResponse> typeCustomerFunction(TypeCustomerHandler typeCustomerHandler) {
		return  RouterFunctions.route(POST("/api/v1/func/type-customers").and(accept(MediaType.APPLICATION_JSON)),typeCustomerHandler::addTypeCustomer);
				
	}
}
