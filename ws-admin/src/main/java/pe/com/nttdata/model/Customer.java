package pe.com.nttdata.model;

import java.util.List;

import lombok.Data;

@Data
public class Customer {
	private String id;
	private String name;
	private String surname;
	private String documentNumber;
	private String cellphoneNumber;
	private String email;
	private List<TypeCustomer>typeCustomers;
	private List<Product>  products;
}
