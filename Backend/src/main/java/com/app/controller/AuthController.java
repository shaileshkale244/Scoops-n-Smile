package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResponse;
import com.app.security.JwtUtil;
import com.app.security.UnifiedUserDetailsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UnifiedUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid credentials", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails);
		String authority = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
//		System.out.println(authority);
		return ResponseEntity.ok().body(new AuthResponse(jwt, userDetails,authority));
	}

}
