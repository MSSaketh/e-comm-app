package com.capgemini.productcatalog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productcatalog.domain.Product;
import com.capgemini.productcatalog.service.ProductService;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@PostMapping("product")
	public ResponseEntity<?> saveMovieHandler(@RequestBody Product product) {

		Product productItem = productService.addProduct(product);
		logger.debug("Product: {}", productItem);
		return new ResponseEntity<Product>(productItem, HttpStatus.CREATED);

	}

	@GetMapping("products")
	public ResponseEntity<List<Product>> getProducts() {

		List<Product> products = productService.allProducts();
		
		logger.debug("Products: {}", products);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

}
