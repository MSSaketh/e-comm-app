package com.capgemini.productcart.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.productcart.domain.Cart;
import com.capgemini.productcart.domain.CartItem;

@Repository
public class ProductCartRepositoryImpl implements ProductCartRepository {

	private static final Logger logger = LoggerFactory.getLogger(ProductCartRepositoryImpl.class);

	@Autowired
	private final RedisTemplate<String, Cart> redisTemplate;

	
	public ProductCartRepositoryImpl(RedisTemplate<String, Cart> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}


	@Override
	public Cart findByCartId(String id) {
		return redisTemplate.opsForValue().get(id);
	}

	@Override
	public Cart addToCart(String id, CartItem cartItem) {
		Cart cart = null;
		logger.debug("cart id: {}", id);
		// If cart does not exist.
		if ((id == null) || (id.equalsIgnoreCase(""))) {
			logger.debug("Missing id, creating new cart.");
			cart = createCart(UUID.randomUUID().toString(), cartItem);
		} else {
			cart = findByCartId(id);
			logger.debug("Retrieve existing cart by id: {}, cart: {}", id, cart);
			if (cart == null) {
				cart = createCart(id, cartItem);
			} else {
				cart.getCartItems().add(cartItem);
			}
		}

		// cart.getItems().stream().mapToDouble(CartItem::getPrice).sum()
		cart.setCartTotal(cart.getCartItems().stream().map(CartItem::getItemPrice).reduce(Float::sum)
				.orElseGet(() -> new Float(0)));
		logger.debug("cart: " + cart);
		redisTemplate.opsForValue().set(cart.getCartId(), cart);
		return cart;
	}

	private Cart createCart(String id, CartItem cartItem) {
		List<CartItem> cartItems = new ArrayList<CartItem>();
		cartItems.add(cartItem);
		Cart cart = new Cart(id, cartItems, cartItem.getItemPrice());
		// If cart by id exist, then add to it.
		return cart;
	}

}
