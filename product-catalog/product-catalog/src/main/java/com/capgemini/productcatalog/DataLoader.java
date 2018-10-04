package com.capgemini.productcatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.capgemini.productcatalog.domain.Product;
import com.capgemini.productcatalog.repository.ProductRepository;

public class DataLoader implements ApplicationRunner {
	
	private ProductRepository productService;
	
	@Autowired
	public DataLoader(ProductRepository productService) {
		super();
		this.productService = productService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Product prod1 = new Product("1","Asus","Asus Laptop",54000,"https://assets.gadgets360cdn.com/shop/assets/products/laptop-asus-x540sa-xx004d-15-6-inch-laptop_1476442041.jpeg?downsize=390:*&output-quality=80&output-format=jpg");
		productService.insert(prod1);
		
		Product prod2 = new Product("2","Asus","Asus Laptop",54000,"https://assets.gadgets360cdn.com/shop/assets/products/laptop-asus-x540sa-xx004d-15-6-inch-laptop_1476442041.jpeg?downsize=390:*&output-quality=80&output-format=jpg");
		productService.insert(prod2);
		
		Product prod3 = new Product("3","Asus","Asus Laptop",54000,"https://assets.gadgets360cdn.com/shop/assets/products/laptop-asus-x540sa-xx004d-15-6-inch-laptop_1476442041.jpeg?downsize=390:*&output-quality=80&output-format=jpg");
		productService.insert(prod3);
	}

}
