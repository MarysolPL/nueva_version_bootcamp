package pe.com.nttdata.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
@Document(collection = "product")
public class Product {
	@Id
	private String id;
	private double commission;
	private int numberOfMovements; 
	private int numberOfCredit; 
	private double amount; 
	private int limitCredit;
	private String action;
	private String idTypeProduct;
	private String idCustomer;
	
	

}
