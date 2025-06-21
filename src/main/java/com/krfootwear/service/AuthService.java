package com.krfootwear.service;

import com.krfootwear.dto.LoginRequest;
import com.krfootwear.dto.RegisterRequest;
import com.krfootwear.entity.User;
import com.krfootwear.repository.UserRepository;
import com.krfootwear.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private JwtUtil jwtUtil;

	public String register(RegisterRequest request) {
		User user = new User();
		user.setUsername(request.username);
		user.setEmail(request.email);
		user.setPassword(passwordEncoder.encode(request.password));
		userRepository.save(user);
		return jwtUtil.generateToken(user.getEmail());
	}

	public String login(LoginRequest request) {
		User user = userRepository.findByEmail(request.email)
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return jwtUtil.generateToken(user.getEmail());
	}
}
