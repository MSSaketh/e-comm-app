package com.capgemini.productcart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.productcart.domain.Cart;
import com.capgemini.productcart.domain.CartItem;
import com.capgemini.productcart.repository.ProductCartRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductCartController {

	private static final Logger logger = LoggerFactory.getLogger(ProductCartController.class);

	private ProductCartRepository productCartRepo;

	@Autowired
	public ProductCartController(ProductCartRepository productCartRepo) {
		super();
		this.productCartRepo = productCartRepo;
	}

	@GetMapping(value = "cart/{id}")
	public Cart cart(@PathVariable("id") String id) {
		logger.debug("Received request for cart by id: {}", id);
		Cart cart = productCartRepo.findByCartId(id);
		logger.debug("Cart: {}", cart);
		return cart;
	}

	@PutMapping(value = "cart/{id}")
	public Cart cart(@PathVariable("id") String id, @RequestBody CartItem cartItem) {
		logger.debug("Received request to add item to cart by id: {}", id);
		Cart cart = productCartRepo.addToCart(id, cartItem);
		logger.debug("Cart: {}", cart);
		return cart;
	}

	@PostMapping(value = "cart")
	public Cart cart(@RequestBody CartItem cartItem) {
		logger.debug("Received request to add item to cart without id.");
		Cart cart = productCartRepo.addToCart(null, cartItem);
		logger.debug("Cart: {}", cart);
		return cart;
	}

}
