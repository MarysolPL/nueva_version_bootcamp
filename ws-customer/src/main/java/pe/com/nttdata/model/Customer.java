package pe.com.nttdata.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "customer")
public class Customer {
	@Id
	private String id;
	private String name;
	private String surname;
	
	
	
	

}
