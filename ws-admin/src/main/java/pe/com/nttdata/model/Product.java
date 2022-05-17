package pe.com.nttdata.model;


import lombok.Data;

@Data
public class Product {
	
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
