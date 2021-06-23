package com.mobiquity.atms;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mobiquity.atms.controllers.AtmsController;

@SpringBootTest
public class SmokeTest {
	
	@Autowired
	private AtmsController atmsController;
	
	@Test
	void contextLoads() {
		assertThat(atmsController).isNotNull();
	}

}
