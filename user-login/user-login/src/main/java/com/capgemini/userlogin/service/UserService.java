package com.capgemini.userlogin.service;

import com.capgemini.userlogin.domain.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public User findByEmail(String email);
	
	

}
