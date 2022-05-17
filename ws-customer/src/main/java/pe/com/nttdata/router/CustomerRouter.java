package pe.com.nttdata.router;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.nttdata.handler.CustomerHandler;
import pe.com.nttdata.util.Constantes;

@Configuration
public class CustomerRouter {
	@Bean
	public RouterFunction<ServerResponse> typeCustomerFunction(CustomerHandler customerHandler) {
		return  RouterFunctions.route(POST(Constantes.URL_PATH_FUNC).and(accept(MediaType.APPLICATION_JSON)),customerHandler::addCustomer)
				.andRoute(GET(Constantes.URL_PATH_FUNC).and(accept(MediaType.APPLICATION_JSON)),customerHandler::getAllCustomer)
				.andRoute(GET(Constantes.URL_PATH_FUNC.concat("/{id}")).and(accept(MediaType.APPLICATION_JSON)),customerHandler::findByIdCustomer)
				.andRoute(DELETE(Constantes.URL_PATH_FUNC).and(accept(MediaType.APPLICATION_JSON)),customerHandler::deleteCustomer);
	}
}
