package com.capgemini.productcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.capgemini.productcart.domain.Cart;

@EnableDiscoveryClient
@EnableRedisRepositories
@SpringBootApplication
public class ProductCartApplication {

//	192.168.43.218

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
		return new JedisConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<String, Cart> redisTemplate() {
		RedisTemplate<String, Cart> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Cart>(Cart.class));
		return redisTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductCartApplication.class, args);
	}


}
