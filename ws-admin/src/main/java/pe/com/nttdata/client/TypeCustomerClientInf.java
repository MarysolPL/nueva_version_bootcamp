package pe.com.nttdata.client;

import java.util.List;

import pe.com.nttdata.model.TypeCustomer;

public interface TypeCustomerClientInf {
	List<TypeCustomer>searchByIdCustomer(String idCustomer);
}
