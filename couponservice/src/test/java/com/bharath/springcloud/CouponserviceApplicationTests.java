package com.bharath.springcloud;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CouponserviceApplicationTests {

	@Test
	void contextLoads(ApplicationContext appCxt) {
		assertThat(appCxt).isNotNull();

	}


}
