package com.tiagoalmeida.skateshopstore.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.tiagoalmeida.skateshopstore.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
