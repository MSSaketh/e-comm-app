package com.capgemini.productcart.repository;

import com.capgemini.productcart.domain.Cart;
import com.capgemini.productcart.domain.CartItem;


public interface ProductCartRepository {

	public Cart findByCartId(String id);

	public Cart addToCart(String id, CartItem cartItem);

}
