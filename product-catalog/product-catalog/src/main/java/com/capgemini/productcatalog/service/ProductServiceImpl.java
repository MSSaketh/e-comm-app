package com.capgemini.productcatalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.productcatalog.domain.Product;
import com.capgemini.productcatalog.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepo;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepo) {
		super();
		this.productRepo = productRepo;
	}

	@Override
	public Product addProduct(Product product) {
		Product addProduct = productRepo.insert(product);		
		return addProduct;
	}

	@Override
	public List<Product> allProducts() {
		List<Product> getProducts = productRepo.findAll();
		return getProducts;
	}

}
