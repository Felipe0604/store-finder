package com.jumbo.store.finder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinderApplicationTests {

	@Test
	void mainTest() {
		Assertions.assertAll( () -> FinderApplication.main( new String[] {"args"}));
	}

}
