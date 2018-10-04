package com.capgemini.productcart.domain;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Cart")
public class Cart {

	private String cartId;
	private List<CartItem> cartItems;
	private float cartTotal;

	public Cart() {
		super();
	}

	public Cart(String cartId, List<CartItem> cartItems, float cartTotal) {
		super();
		this.cartId = cartId;
		this.cartItems = cartItems;
		this.cartTotal = cartTotal;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public float getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(float cartTotal) {
		this.cartTotal = cartTotal;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartItems=" + cartItems + ", cartTotal=" + cartTotal + "]";
	}
	
	

}
