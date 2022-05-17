package pe.com.nttdata.router;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import pe.com.nttdata.handler.ProductHandler;
import pe.com.nttdata.util.Constantes;

@Configuration
public class ProductRouter {
	@Bean
	public RouterFunction<ServerResponse> typeCustomerFunction(ProductHandler productHandler) {
		return  RouterFunctions.route(POST(Constantes.URL_PATH_FUNC).and(accept(MediaType.APPLICATION_JSON)),productHandler::addProduct)
				.andRoute(GET(Constantes.URL_PATH_FUNC).and(accept(MediaType.APPLICATION_JSON)),productHandler::getAllProduct)
				.andRoute(GET(Constantes.URL_PATH_FUNC.concat("/{id}")).and(accept(MediaType.APPLICATION_JSON)),productHandler::findByIdProduct)
				.andRoute(DELETE(Constantes.URL_PATH_FUNC).and(accept(MediaType.APPLICATION_JSON)),productHandler::deleteProduct);
	}
}
