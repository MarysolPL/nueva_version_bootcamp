package pe.com.nttdata.model;


import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@AllArgsConstructor

@Data
@Document(collection = "type_product")
public class TypeProduct {
	@Id
	private String id;
	private String type;
	private String account ; 
	private boolean status; 
	
	

}
