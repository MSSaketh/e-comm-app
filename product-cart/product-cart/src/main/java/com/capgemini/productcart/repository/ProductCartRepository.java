package com.capgemini.productcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.productcart.domain.Cart;

public interface ProductCartRepository extends CrudRepository<Cart, String> {
	
	

}
