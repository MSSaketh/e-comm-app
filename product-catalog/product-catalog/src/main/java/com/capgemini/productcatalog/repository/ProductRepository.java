package com.capgemini.productcatalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.productcatalog.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	public Product findByProductId(String id);

}
