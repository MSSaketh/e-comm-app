package com.capgemini.userlogin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.userlogin.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByEmail(String email);

}
