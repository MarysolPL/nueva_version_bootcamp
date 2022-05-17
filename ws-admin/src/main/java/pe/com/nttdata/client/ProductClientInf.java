package pe.com.nttdata.client;

import java.util.List;

import pe.com.nttdata.model.Product;
import pe.com.nttdata.model.TypeProduct;

public interface ProductClientInf {
	public Product findByIdCustomer(String id);
	public Product  save(Product product);
	public Product  findById(String id);
	
	public List<TypeProduct> getAllTypeProduct();
	public List<Product>  findByIdCustomers(String id);
	

}
