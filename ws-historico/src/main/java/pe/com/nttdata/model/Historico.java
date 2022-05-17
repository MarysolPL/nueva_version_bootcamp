package pe.com.nttdata.model;


import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
@Document(collection = "historico")

public class Historico {
	@Id
	private String id;
	private double montoActual;
	private String idOpreracion;
	private double commission;
	private int numberOfMovements; 
	private int numberOfCredit; 
	private int limitCredit;
	private String action;
	private String idTypeProduct;
	private String idCustomer;
	
	private String fechaOperacion;
	private String device;
	
	

}
