package com.virtadity.manease;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@Import(AppTestContainersConfig.class)
@ActiveProfiles("test")
class ManeaseApplicationTests {

	@Test
	void contextLoads() {

	}

}
