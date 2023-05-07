package com.bharath.springcloud;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CouponserviceApplicationTests {

	@Test
	void contextLoads(ApplicationContext appCxt) {
		assertThat(appCxt).isNotNull();

	}

	@Test
	void testPWDEncoders() {
		System.out.println(new Pbkdf2PasswordEncoder("secret", 10000, 128,
				Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256).encode("password"));

		System.out.println(SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8().encode("password"));
		System.out.println(new BCryptPasswordEncoder().encode("password"));

		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());

		System.out.println(new DelegatingPasswordEncoder("pbkdf2", encoders).encode("password"));

	}


}
