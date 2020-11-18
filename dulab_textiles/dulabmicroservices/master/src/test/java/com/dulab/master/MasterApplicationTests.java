package com.dulab.master;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class MasterApplicationTests {

	@Test
	void contextLoads() {
		try {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

}
