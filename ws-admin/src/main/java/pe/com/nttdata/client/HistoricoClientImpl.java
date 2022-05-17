package pe.com.nttdata.client;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import pe.com.nttdata.model.Historico;



@Service
public class HistoricoClientImpl implements HistoricoClientInf{
	private static final Logger log = LoggerFactory.getLogger(HistoricoClientImpl.class);
	
	@Override
	public Historico save(Historico historico) {
		long tiempoInicio = System.currentTimeMillis();
		Historico response =new Historico(); 
		ObjectMapper objectMapper = new ObjectMapper();
		String responseJson = null;

		String url = "http://localhost:8882/historicos";
		Integer timeoutConexion= 4;
		Integer timeoutEjecucion = 4;

		try {

			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	
			

			log.info("URL historicio: {}",url);
			log.info("Tipo: {}", HttpMethod.POST);
			Gson gson = new Gson();
			String requesGson= gson.toJson(historico);
			log.info("reques: {}",requesGson);
			HttpEntity<String> httpEntity = new HttpEntity<>(requesGson,httpHeaders);

			SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
			httpRequestFactory.setConnectTimeout(4);
			httpRequestFactory.setReadTimeout(4);

			log.info("Timeout conexion (ms): {}" , timeoutConexion);
			log.info("Timeout ejecucion (ms): {}" , timeoutEjecucion);

		
			RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					url, HttpMethod.POST, httpEntity, String.class);

			log.info("Servicio REST ejecutado");

			responseJson = responseEntity.getBody();
			response = objectMapper.readValue(responseJson, Historico.class);

		}catch (HttpClientErrorException e) {
			e.printStackTrace();
		
			log.error("Codigo Error: {}", e.getStatusCode());
			log.error("Mensaje Error: {}", e.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error en modificar: {} {}" , e.getMessage(), e);

		} finally {
			log.info("Datos de Salida:\n {}" , responseJson);
			log.info("Tiempo invocacion: {} {}" , (System.currentTimeMillis() - tiempoInicio)
					, " milisegundos");
		}

		return response;
	
	}

	

}
