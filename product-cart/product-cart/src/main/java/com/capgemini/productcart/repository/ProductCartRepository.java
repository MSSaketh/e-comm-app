package com.capgemini.productcart.repository;

import org.springframework.stereotype.Repository;

import com.capgemini.productcart.domain.Cart;
import com.capgemini.productcart.domain.CartItem;

@Repository
public interface ProductCartRepository {

	public Cart findByCartId(String id);

	public Cart addToCart(String id, CartItem cartItem);

}
