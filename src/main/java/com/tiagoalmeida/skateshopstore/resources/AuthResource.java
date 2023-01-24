package com.tiagoalmeida.skateshopstore.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiagoalmeida.skateshopstore.security.JWTUtil;
import com.tiagoalmeida.skateshopstore.security.UserSS;
import com.tiagoalmeida.skateshopstore.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generationToken(user.getUsername());
		response.addHeader("Authorization", "Bearer "+token);
		return ResponseEntity.noContent().build();
	}

}
