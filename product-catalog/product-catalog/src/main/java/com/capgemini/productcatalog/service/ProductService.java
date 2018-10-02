package com.capgemini.productcatalog.service;

import java.util.List;

import com.capgemini.productcatalog.domain.Product;

public interface ProductService {
	
	public Product addProduct(Product product);
	
	public List<Product> allProducts();
	
	

}
