package com.capgemini.userlogin.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.userlogin.domain.Token;
import com.capgemini.userlogin.domain.User;
import com.capgemini.userlogin.exceptions.PasswordNotMatchException;
import com.capgemini.userlogin.exceptions.UserNameNotFoundException;
import com.capgemini.userlogin.exceptions.UserNameOrPasswordEmpty;
import com.capgemini.userlogin.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		logger.info("User Registration successfull");
		return userService.saveUser(user);
	}

	@GetMapping("/get")
	public ResponseEntity<User> getUser(@RequestParam String email) {
		User getUser = userService.findByEmail(email);
		logger.info(getUser.getEmail());
		return new ResponseEntity<User>(getUser, HttpStatus.FOUND);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User login) throws ServletException {

		String jwtToken = "";
		try {
			if (login.getEmail() == null || login.getPassword() == null) {
				logger.warn("Please fill in username and password");
				throw new UserNameOrPasswordEmpty("Please fill in username and password");
			}
		} catch (UserNameOrPasswordEmpty e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

		String email = login.getEmail();
		String password = login.getPassword();

		User user = userService.findByEmail(email);

		try {
			if (user == null) {
				logger.warn("User email not found.");
				throw new UserNameNotFoundException("User email not found.");
			}
		} catch (UserNameNotFoundException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

		String pwd = user.getPassword();

		try {
			if (!password.equals(pwd)) {
				logger.warn("Invalid login. Please check your name and password.");
				throw new PasswordNotMatchException("Invalid login. Please check your name and password.");
			}
		} catch (PasswordNotMatchException e) {
			return new ResponseEntity<String>(e.toString(), HttpStatus.CONFLICT);
		}

		jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Token token = new Token();
		token.setToken(jwtToken);
		logger.info("Generated Token is: " + jwtToken);
		return new ResponseEntity<Token>(token, HttpStatus.CREATED);
	}

}
