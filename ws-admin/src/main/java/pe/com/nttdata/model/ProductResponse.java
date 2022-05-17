package pe.com.nttdata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper=false)
@Data
public class ProductResponse extends GenericResponse{
	private Product product;
}
