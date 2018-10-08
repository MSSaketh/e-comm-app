package com.capgemini.productcart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.capgemini.productcart.domain.Cart;

@EnableDiscoveryClient
@EnableRedisRepositories
@SpringBootApplication
public class ProductCartApplication {

//	192.168.43.218
	private static final Logger logger = LoggerFactory.getLogger(ProductCartApplication.class);

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
		return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<String, Cart> redisTemplate() {
		RedisTemplate<String, Cart> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		redisTemplate.setValueSerializer(new GenericToStringSerializer<Cart>(Cart.class));Jackson2JsonRedisSerializer
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Cart>(Cart.class));
		return redisTemplate;
	}
	
//	@Bean
//	public CommandLineRunner commandLineRunner(ProductCartRepository cartRepository, RedisTemplate<String, Cart> redisTemplate) {
//		return strings -> {
//			redisTemplate.delete("123");
//			logger.info("Test");
//			CartItem cartItem = new CartItem(null,"new product",2.3f);
//			Cart cart = cartRepository.addToCart(null, cartItem);
//			logger.info("Cart : {}.", cartRepository.findByCartId(cart.getCartId()));
//			logger.info("Find 123 : {}.", cartRepository.findByCartId("123"));
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCartApplication.class, args);
	}


}
