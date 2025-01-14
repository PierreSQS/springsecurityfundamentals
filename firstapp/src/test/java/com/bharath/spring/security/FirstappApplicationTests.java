package com.bharath.spring.security;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootTest
class FirstappApplicationTests {

	@Test
	void testPasswordEncoders() {
		System.out.println(new BCryptPasswordEncoder().encode("password"));
		System.out.println(Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8().encode("password"));
		System.out.println(SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8().encode("password"));
		
		Map<String, PasswordEncoder> encoders =new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
		
		System.out.println(new DelegatingPasswordEncoder("bcrypt", encoders).encode("password"));

	}

}
